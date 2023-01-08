package org.jesperancinha.xml.adder

import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldHave
import io.kotest.matchers.string.shouldHaveLength
import org.gradle.internal.impldep.org.junit.rules.TemporaryFolder
import org.jesperancinha.xml.adder.XmlAdderManager.Companion.listAllFilesToChange
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.w3c.dom.Document
import org.xml.sax.SAXException
import java.io.*
import java.util.*
import java.util.regex.Pattern
import javax.xml.parsers.ParserConfigurationException
import javax.xml.transform.TransformerException
import javax.xml.xpath.XPathExpressionException

/**
 * Created by joaofilipesabinoesperancinha on 18-02-16.
 */
class XmlAdderManagerTest2 {
    @Test
    @Throws(IOException::class, ParserConfigurationException::class)
    fun testReadAllAddAttributes() {
        val inputStream = javaClass.getResourceAsStream("/testReadAttributeBean.csv")
        val ruleStream: InputStream = FileInputStream(File.createTempFile("test","xml-adder"))
        val manager: XmlAdderManager = object : XmlAdderManager(null, null, inputStream, null, ruleStream) {
            @Throws(IOException::class)
            override fun getRuleFromIO(fileRule: InputStream?): String {
                return "null"
            }
        }
        val attributeManager = manager.addAttributeManager
        val result = attributeManager.getXmlAdderInstructionArrayMap()
        val resultSet = result.keys
        resultSet.shouldHaveSize(1)
        for (key in resultSet) {
            key shouldBe "testnode1/testnode2"
            val xmlAdderInstruction = result[key]
            xmlAdderInstruction.shouldNotBeNull()
            xmlAdderInstruction.getAttributesToAdd()["attribute1name"] shouldBe "attribute1value"
        }
    }

    @Test
    @Throws(
        SAXException::class,
        TransformerException::class,
        XPathExpressionException::class,
        IOException::class,
        ParserConfigurationException::class
    )
    fun testCompleteProcess() {
        val inputStreamRule = javaClass.getResourceAsStream("/testDesc.txt")
        val inputStreamAttributeBean = javaClass.getResourceAsStream("/testDescAddBean.csv")
        val inputStreamAttributeDeleteBean = javaClass.getResourceAsStream("/testDescDeleteBean.csv")
        val testFolder = getTestFolder(true)
        val manager: XmlAdderManager = object : XmlAdderManager(
            testFolder,
            File("/tmp"),
            inputStreamAttributeBean,
            inputStreamAttributeDeleteBean,
            inputStreamRule
        ) {
            @Throws(TransformerException::class)
            override fun saveFile(file: File, doc: Document?) {
                super.saveFile(file, doc)
            }
        }
        val attributeManager = manager.addAttributeManager
        val resultAdd = attributeManager.getXmlAdderInstructionArrayMap()
        val resultSetAdd = resultAdd.keys
        resultSetAdd.shouldHaveSize(2)
        for (key in resultSetAdd) {
            val xmlAdderInstruction = resultAdd[key]
            xmlAdderInstruction.shouldNotBeNull()
            when (key) {
                "testnode1/testnode2" -> {
                    val attributes = xmlAdderInstruction.getAttributesToAdd()["attribute1name"]
                    attributes.shouldHaveLength(0)
                }
                "testnode1/testnode3" ->
                    xmlAdderInstruction.getAttributeKeysToDelete().shouldHaveSize(1)
                else -> fail("Extra case found!")
            }
        }
        manager.runConversion()
    }

    @Test
    @Throws(IOException::class)
    fun `should present empty results when no files are to be added`() {
        val resultFilesToChange = listAllFilesToChange(getTestFolder(false))
        resultFilesToChange.shouldBeEmpty()
    }

    @Test
    @Throws(IOException::class)
    fun testlistAllFilesToChange_oneXmlInEachFolder() {
        val resultFilesToChange = listAllFilesToChange(getTestFolder(true))
        resultFilesToChange.shouldHaveSize(6)
        resultFilesToChange.map { file: File -> file.name }
            .sorted()
            .shouldContainExactly(
                listOf(
                    "file1.xml",
                    "file11.xml",
                    "file12.xml",
                    "file2.xml",
                    "file21.xml",
                    "file22.xml"
                )
            )

    }

    @Test
    @Throws(IOException::class, ParserConfigurationException::class)
    fun testGetRule() {
        testGetRule("/testRule0.txt", "I am a", testGUID = true, testUpperCase = false)
        testGetRule("/testRule0.1.txt", "I am a", testGUID = true, testUpperCase = false)
        testGetRule("/testRule1.txt", "I am not a GUID", testGUID = false, testUpperCase = false)
        testGetRule("/testRule2.txt", "I am a slash behind a \\", testGUID = true, testUpperCase = false)
        testGetRule("/testRule3.txt", "I am two slashes \\\\", testGUID = true, testUpperCase = false)
        testGetRule("/testRule4.txt", "I am two slashes and not a \\\\GUID", testGUID = false, testUpperCase = false)
    }

    @Throws(IOException::class, ParserConfigurationException::class)
    fun testGetRule(testRule: String?, testRuleMatch: String?, testGUID: Boolean, testUpperCase: Boolean) {
        val manager: XmlAdderManager = object : XmlAdderManager(
            null,
            null,
            null,
            null,
            this::class.java.getResourceAsStream(testRule)
        ) {
            @Throws(IOException::class)
            override fun readAllAddAttributes(fileAddAttributes: InputStream?) {
            }
        }
        val resultRule = manager.getGeneralRule()
        if (testGUID) {
            val pattern = Pattern.compile(guidMatch)
            val matcher = pattern.matcher(resultRule)
            try {
                matcher.find().shouldBeTrue()
            } catch (e: Error) {
                throw RuntimeException("Match GUUID not found! Got: $resultRule", e)
            }
            var resultMatch = resultRule.replace(matcher.group(0), "").trim { it <= ' ' }
            if (testUpperCase) {
                resultMatch = resultMatch.uppercase(Locale.getDefault())
            }
            resultMatch shouldBe testRuleMatch
        } else {
            resultRule shouldBe testRuleMatch
        }
    }

    @Throws(IOException::class)
    private fun getTestFolder(addFiles: Boolean): File {
        val folder = TemporaryFolder()
        folder.create()
        val structure = folder.newFolder("structure")
        val folder1 = File(structure, "folder1")
        folder1.deleteOnExit()
        folder1.mkdir()
        val folder11 = File(folder1, "folder11")
        folder11.deleteOnExit()
        folder11.mkdir()
        val folder12 = File(folder1, "folder12")
        folder12.deleteOnExit()
        folder12.mkdir()
        val folder2 = File(structure, "folder2")
        folder2.deleteOnExit()
        folder2.mkdir()
        val folder21 = File(folder2, "folder21")
        folder21.deleteOnExit()
        folder21.mkdir()
        val folder22 = File(folder2, "folder22")
        folder22.deleteOnExit()
        folder22.mkdir()
        if (addFiles) {
            createFile(folder1, "file1", "<testnode1><testnode2></testnode2></testnode1>")
            createFile(
                folder11,
                "file11",
                "<testnode1><testnode3 attribute13name=\"attribute13nameValue\"></testnode3></testnode1>"
            )
            createFile(folder12, "file12", "<testnode1><testnode4></testnode4></testnode1>")
            createFile(folder2, "file2", "<testnode1><testnode5></testnode5></testnode1>")
            createFile(folder21, "file21", "<testnode1><testnode6></testnode6></testnode1>")
            createFile(folder22, "file22", "<testnode1><testnode2></testnode2></testnode1>")
        }
        return structure
    }

    @Throws(IOException::class)
    private fun createFile(folder: File, fileName: String, content: String) {
        val fileElement = File(folder, "$fileName.xml")
        fileElement.deleteOnExit()
        fileElement.createNewFile()
        val fw = FileWriter(fileElement)
        fw.write(content)
        fw.flush()
        fw.close()
    }

    companion object {
        private const val guidMatch =
            "([0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12})"
    }
}