package org.jesperancinha.xml.adder

import org.hamcrest.collection.IsCollectionWithSize
import org.hamcrest.core.IsCollectionContaining
import org.jesperancinha.xml.adder.XmlAdderManager.Companion.listAllFilesToChange
import org.jmock.Mockery
import org.jmock.lib.legacy.ClassImposteriser
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.xml.sax.SAXException
import java.io.*
import java.util.*
import java.util.regex.Pattern
import java.util.stream.Collectors
import javax.xml.parsers.ParserConfigurationException
import javax.xml.transform.TransformerException
import javax.xml.xpath.XPathExpressionException

/**
 * Created by joaofilipesabinoesperancinha on 18-02-16.
 */
class XmlAdderManagerTest {
    @Before
    fun setupMocks() {
        context.setImposteriser(ClassImposteriser.INSTANCE)
    }

    @Test
    @Throws(IOException::class, ParserConfigurationException::class)
    fun testReadAllAddAttributes() {
        val `is` = javaClass.getResourceAsStream("/testReadAttributeBean.csv")
        val ruleStream: InputStream = context.mock(
            FileInputStream::class.java
        )
        val manager: XmlAdderManager = object : XmlAdderManager(null, null, `is`, null, ruleStream) {
            override fun getRuleFromIO(fileRule: InputStream?): String {
                return null
            }
        }
        val attributeManager = manager.addAttributeManager
        val result = attributeManager.getXmlAdderInstructionArrayMap()
        val resultSet = result.keys
        Assert.assertEquals(1, resultSet.size.toLong())
        for (key in resultSet) {
            Assert.assertEquals("testnode1/testnode2", key)
            Assert.assertEquals("attribute1value", result[key]!!.getAttributesToAdd()["attribute1name"])
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
        val inputStreamAttributeBean = javaClass.getResourceAsStream("/testDescBean.csv")
        val testFolder = getTestFolder(true)
        val manager = XmlAdderManager(
            testFolder,
            File("/tmp"),
            inputStreamAttributeBean,
            null, inputStreamRule
        )
        val attributeManager = manager.addAttributeManager
        val result = attributeManager.getXmlAdderInstructionArrayMap()
        val resultSet = result.keys
        Assert.assertEquals(1, resultSet.size.toLong())
        for (key in resultSet) {
            Assert.assertEquals("testnode1/testnode2", key)
            Assert.assertEquals(0, result[key]!!.getAttributesToAdd()["attribute1name"]!!.length.toLong())
        }
        manager.runConversion()
    }

    @Test
    @Throws(IOException::class)
    fun testlistAllFilesToChange_empty() {
        val resultFilesToChange = listAllFilesToChange(getTestFolder(false))
        Assert.assertEquals(0, resultFilesToChange.size.toLong())
    }

    @Test
    @Throws(IOException::class)
    fun testlistAllFilesToChange_oneXmlInEachFolder() {
        val resultFilesToChange = listAllFilesToChange(getTestFolder(true))
        Assert.assertThat(resultFilesToChange, IsCollectionWithSize.hasSize(6))
        Assert.assertThat(resultFilesToChange.stream().map { obj: File -> obj.name }
            .collect(Collectors.toList()),
            IsCollectionContaining.hasItems(
                "file1.xml",
                "file11.xml",
                "file12.xml",
                "file2.xml",
                "file21.xml",
                "file22.xml"
            ))
    }

    @Test
    @Throws(IOException::class, ParserConfigurationException::class)
    fun testGetRule() {
        testGetRule("/testRule0.txt", "I am a", true, false)
        testGetRule("/testRule0.1.txt", "I am a", true, false)
        testGetRule("/testRule1.txt", "I am not a GUID", false, false)
        testGetRule("/testRule2.txt", "I am a slash behind a \\", true, false)
        testGetRule("/testRule3.txt", "I am two slashes \\\\", true, false)
        testGetRule("/testRule4.txt", "I am two slashes and not a \\\\GUID", false, false)
    }

    @Throws(IOException::class, ParserConfigurationException::class)
    fun testGetRule(testRule: String?, testRuleMatch: String?, testGUID: Boolean, testUpperCase: Boolean) {
        val manager: XmlAdderManager = object : XmlAdderManager(
            null,
            null,
            null,
            null,
            javaClass.getResourceAsStream(testRule)
        ) {
            @Throws(IOException::class)
            override fun readAllAddAttributes(fileAddAttributes: InputStream?) {
            }
        }
        val resultRule = manager.getRule()
        if (testGUID) {
            val pattern = Pattern.compile(guidMatch)
            val matcher = pattern.matcher(resultRule)
            try {
                Assert.assertTrue(matcher.find())
            } catch (e: Error) {
                throw RuntimeException("Match GUUID not found! Got: $resultRule", e)
            }
            var resultMatch = resultRule.replace(matcher.group(0), "").trim { it <= ' ' }
            if (testUpperCase) {
                resultMatch = resultMatch.uppercase(Locale.getDefault())
            }
            Assert.assertEquals(testRuleMatch, resultMatch)
        } else {
            Assert.assertEquals(testRuleMatch, resultRule)
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
            createFile(folder11, "file11", "<testnode1><testnode3></testnode3></testnode1>")
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
        private val context = Mockery()
    }
}