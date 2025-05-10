package org.jesperancinha.xml.adder

import com.opencsv.CSVReader
import com.opencsv.bean.ColumnPositionMappingStrategy
import com.opencsv.bean.CsvToBean
import com.opencsv.bean.HeaderColumnNameMappingStrategy
import org.apache.commons.io.IOUtils
import org.jesperancinha.xml.adder.csv.AttributeAddBean
import org.jesperancinha.xml.adder.csv.AttributeDeleteBean
import org.jesperancinha.xml.adder.instruction.XmlAdderAddAttributeManager
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import org.xml.sax.SAXException
import java.io.*
import java.util.*
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.*
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathExpressionException
import javax.xml.xpath.XPathFactory

/**
 * Created by joaofilipesabinoesperancinha on 16-02-16.
 */
open class XmlAdderManager(
    private val fileSourceDirectory: File? = null,
    private val fileDestinationDirectory: File? = null,
    fileAddAttributes: InputStream?,
    fileDeleteAttributes: InputStream?,
    fileRule: InputStream?
) {
    // Document parsing setup instances
    private val factory = DocumentBuilderFactory.newInstance()
    private var builder: DocumentBuilder? = null
    private val xPathfactory = XPathFactory.newInstance()
    private val xpath = xPathfactory.newXPath()
    val rule: String

    @JvmField
    val addAttributeManager = XmlAdderAddAttributeManager()

    init {
        rule = getRuleFromIO(fileRule)
        factory.isNamespaceAware = true
        builder = factory.newDocumentBuilder()
        fileAddAttributes?.let { readAllAddAttributes(it) }
        fileDeleteAttributes?.let { readAllDeleteAttributes(it) }
    }

    private fun readAllDeleteAttributes(fileDeleteAttributes: InputStream) {
        val strategy = HeaderColumnNameMappingStrategy<AttributeDeleteBean>()
        strategy.type = AttributeDeleteBean::class.java
        val csvToBean = CsvToBean<AttributeDeleteBean>()
        csvToBean.setMappingStrategy(strategy)
        csvToBean.setCsvReader(CSVReader(InputStreamReader(fileDeleteAttributes)))
        val beanList = csvToBean.parse()
        for (attBean in beanList) {
            addAttributeManager.addRemoveAttribute(attBean.name, attBean.xpath)
        }
    }

    @Throws(IOException::class)
    protected open fun getRuleFromIO(fileRule: InputStream?): String {
        return IOUtils.toString(fileRule)
    }

    @Throws(IOException::class)
    protected open fun readAllAddAttributes(fileAddAttributes: InputStream?) {
        val strategy = ColumnPositionMappingStrategy<AttributeAddBean>()
        strategy.type = AttributeAddBean::class.java
        val csvToBean = CsvToBean<AttributeAddBean>()
        csvToBean.setMappingStrategy(strategy)
        csvToBean.setCsvReader(CSVReader(InputStreamReader(fileAddAttributes)))
        var beanList = csvToBean.parse()
        beanList = beanList.subList(1, beanList.size)
        for (attBean in beanList) {
            addAttributeManager.addAddAttribute(attBean.name, attBean.value, attBean.xpath)
        }
    }

    @Throws(IOException::class, SAXException::class, XPathExpressionException::class, TransformerException::class)
    fun runConversion() {
        val allXmlFilesToChange = fileSourceDirectory?.let {
            listAllFilesToChange(
                fileSourceDirectory
            )
        } ?: emptyList()
        for (file in allXmlFilesToChange) {
            val doc = getDocument(file)
            var saveFile = false
            for (xpathString in addAttributeManager.xmlAdderInstructionArrayMap.keys) {
                val instruction = addAttributeManager.xmlAdderInstructionArrayMap[xpathString]
                val expr = xpath.compile(xpathString)
                val nl = expr.evaluate(doc, XPathConstants.NODESET) as NodeList
                for (i in 0 until nl.length) {
                    val node = nl.item(i)
                    val attributesToAdd = instruction!!.attributesToAdd
                    if (node.nodeType == Node.ELEMENT_NODE) {
                        for (attName in attributesToAdd.keys) {
                            val value = attributesToAdd[attName]
                            if (value == null || value.isEmpty()) {
                                (node as Element).setAttribute(attName, getGeneralRule())
                            } else {
                                (node as Element).setAttribute(attName, value)
                            }
                            saveFile = true
                        }
                        val attributesToDelete = instruction.attributeKeysToDelete
                        for (attName in attributesToDelete) {
                            if ((node as Element).getAttribute(attName).isNotEmpty()
                            ) {
                                node.removeAttribute(attName)
                                saveFile = true
                            }
                        }
                    }
                }
            }
            if (saveFile) {
                saveFile(file, doc)
            }
        }
    }

    @Throws(TransformerException::class)
    protected open fun saveFile(file: File, doc: Document?) {
        val absolutePath = file.absolutePath
        val rootSourceFolder = fileSourceDirectory?.absolutePath
        val rootDestinationFolder = rootSourceFolder?.let {
            fileDestinationDirectory
                ?.absolutePath + "/" + fileSourceDirectory?.name + absolutePath.replace(rootSourceFolder, "")
        }
        val destinationFile = File(rootDestinationFolder)
        File(destinationFile.parent).mkdirs()
        val transformer = TransformerFactory.newInstance().newTransformer()
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes")
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8")
        val output: Result = StreamResult(destinationFile)
        val input: Source = DOMSource(doc)
        transformer.transform(input, output)
    }

    @Throws(IOException::class, SAXException::class)
    protected fun getDocument(f: File?): Document {
        val `is` = FileInputStream(f)
        val doc = builder!!.parse(`is`)
        `is`.close()
        return doc
    }

    @Throws(IOException::class)
    fun getGeneralRule(): String {
        val slashPiece = rule.split("\\\\".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val slashLength = slashPiece.size
        var slashCounter = 0
        val buffer = StringBuffer()
        for (i in 0 until slashLength) {
            val currentSlash = slashPiece[i]
            if (currentSlash == "") {
                slashCounter++
                if (slashCounter == 2) {
                    buffer.append("\\")
                    slashCounter = 0
                }
            } else if (currentSlash.startsWith(GUID) && slashCounter == 1) {
                buffer.append(currentSlash)
            } else {
                slashCounter = 1
                if (currentSlash.contains(GUID_PLUS)) {
                    buffer.append(
                        currentSlash.replace(
                            GUID_PLUS,
                            UUID.randomUUID().toString().uppercase(Locale.getDefault())
                        )
                    )
                } else if (currentSlash.contains(GUID)) {
                    buffer.append(currentSlash.replace(GUID, UUID.randomUUID().toString()))
                } else {
                    buffer.append(currentSlash)
                }
            }
        }
        return buffer.toString()
    }

    companion object {
        // Constants
        const val GUID = "GUID"
        const val GUID_PLUS = "+GUID"

        @JvmStatic
        fun listAllFilesToChange(sourceDirectory: File): List<File> {
            val allXmlsToChahge: MutableList<File> = ArrayList()
            val fileList = sourceDirectory.listFiles()
            for (f in fileList) {
                if (f.isDirectory) {
                    allXmlsToChahge.addAll(listAllFilesToChange(f))
                } else if (f.isFile) {
                    if (f.name.endsWith("xml")) {
                        allXmlsToChahge.add(f)
                    }
                }
            }
            return allXmlsToChahge
        }
    }
}