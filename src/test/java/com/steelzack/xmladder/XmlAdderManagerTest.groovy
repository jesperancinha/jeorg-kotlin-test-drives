package com.steelzack.xmladder

import com.steelzack.xmladder.instruction.XmlAdderAddAttributeManager
import com.steelzack.xmladder.instruction.XmlAdderInstruction
import org.junit.Assert
import org.junit.Test
import org.junit.rules.TemporaryFolder

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by joaofilipesabinoesperancinha on 18-02-16.
 */
class XmlAdderManagerTest {
    private static
    final String guidMatch = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}";

    @Test
    void testReadAllAddAttributes() {
        final InputStream is = getClass().getResourceAsStream("testReadAttributeBean.csv");
        final XmlAdderManager manager = new XmlAdderManager(null, null, is, null)

        final XmlAdderAddAttributeManager attributeManager = manager.getAddAttributeManager();
        final Map<String, XmlAdderInstruction> result = attributeManager.getXmlAdderInstructionArrayMap();
        final resultSet = result.keySet()

        Assert.assertEquals(1, resultSet.size());
        for (String key : resultSet) {
            Assert.assertEquals("testnode1/testnode2", key);
            Assert.assertEquals("attribute1value", result.get(key).getAttributesToAdd().get("attribute1name"));
        }
    }


    @Test
    void testCompleteProcess() {
        final InputStream inputStreamRule = getClass().getResourceAsStream("testDesc.txt")
        final InputStream inputStreamAttributeBean = getClass().getResourceAsStream("testDescBean.csv");
        final File testFolder = getTestFolder(true)
        final XmlAdderManager manager = new XmlAdderManager(testFolder, new File("/tmp"), inputStreamAttributeBean, inputStreamRule)

        final XmlAdderAddAttributeManager attributeManager = manager.getAddAttributeManager();
        final Map<String, XmlAdderInstruction> result = attributeManager.getXmlAdderInstructionArrayMap();
        final resultSet = result.keySet()

        Assert.assertEquals(1, resultSet.size());
        for (String key : resultSet) {
            Assert.assertEquals("testnode1/testnode2", key);
            Assert.assertEquals(null, result.get(key).getAttributesToAdd().get("attribute1name"));
        }
        manager.runConversion();
    }

    @Test
    void testlistAllFilesToChange_empty() {
        final List<File> resultFilesToChange = XmlAdderManager.listAllFilesToChange(getTestFolder(false));

        Assert.assertEquals(0, resultFilesToChange.size());
    }

    @Test
    void testlistAllFilesToChange_oneXmlInEachFolder() {
        final List<File> resultFilesToChange = XmlAdderManager.listAllFilesToChange(getTestFolder(true));

        Assert.assertEquals(6, resultFilesToChange.size());
        Assert.assertEquals("file1.xml", resultFilesToChange.get(0).getName());
        Assert.assertEquals("file11.xml", resultFilesToChange.get(1).getName());
        Assert.assertEquals("file12.xml", resultFilesToChange.get(2).getName());
        Assert.assertEquals("file2.xml", resultFilesToChange.get(3).getName());
        Assert.assertEquals("file21.xml", resultFilesToChange.get(4).getName());
        Assert.assertEquals("file22.xml", resultFilesToChange.get(5).getName());
    }

    @Test
    void testGetRule() {
        testGetRule("testRule0.txt", "I am a", true, false);
        testGetRule("testRule0.1.txt", "I am a", true, false);
        testGetRule("testRule1.txt", "I am not a GUID", false, false);
        testGetRule("testRule2.txt", "I am a slash behind a GUID", false, false);
    }

    void testGetRule(String testRule, String testRuleMatch, boolean testGUID, boolean testUpperCase) {
        final XmlAdderManager manager = new XmlAdderManager( //
                null, //
                null, //
                null, //
                getClass().getResourceAsStream(testRule) //
        ) {
            @Override
            protected void readAllAddAttributes(InputStream fileAddAttributes) throws IOException {
            }
        };

        final String resultRule = manager.getRule();

        if (testGUID) {
            final Pattern pattern = Pattern.compile(guidMatch)
            final Matcher matcher = pattern.matcher(resultRule);
            Assert.assertTrue(matcher.find());
            final String resultMatch = resultRule.replace(matcher.group(0), "").trim();
            if (testUpperCase) {
                resultMatch = resultMatch.toUpperCase();
            }
            Assert.assertEquals(testRuleMatch, resultMatch);
        } else {
            Assert.assertEquals(testRuleMatch, resultRule);
        }
    }

    private File getTestFolder(boolean addFiles) {
        final TemporaryFolder folder = new TemporaryFolder();
        folder.create();
        final File structure = folder.newFolder("structure");
        final File folder1 = new File(structure, "folder1");
        folder1.deleteOnExit();
        folder1.mkdir();
        final File folder11 = new File(folder1, "folder11");
        folder11.deleteOnExit();
        folder11.mkdir();
        final File folder12 = new File(folder1, "folder12");
        folder12.deleteOnExit();
        folder12.mkdir();
        final File folder2 = new File(structure, "folder2");
        folder2.deleteOnExit();
        folder2.mkdir();
        final File folder21 = new File(folder2, "folder21");
        folder21.deleteOnExit();
        folder21.mkdir();
        final File folder22 = new File(folder2, "folder22");
        folder22.deleteOnExit();
        folder22.mkdir();

        if (addFiles) {
            createFile(folder1, "file1", "<testnode1><testnode2></testnode2></testnode1>");
            createFile(folder11, "file11", "<testnode1><testnode3></testnode3></testnode1>");
            createFile(folder12, "file12", "<testnode1><testnode4></testnode4></testnode1>");
            createFile(folder2, "file2", "<testnode1><testnode5></testnode5></testnode1>");
            createFile(folder21, "file21", "<testnode1><testnode6></testnode6></testnode1>");
            createFile(folder22, "file22", "<testnode1><testnode2></testnode2></testnode1>");

        }
        return structure;
    }

    private void createFile(File folder, String fileName, String content) {
        final File fileElement = new File(folder, fileName + ".xml");
        fileElement.deleteOnExit();
        fileElement.createNewFile();
        final FileWriter fw = new FileWriter(fileElement);
        fw.write(content)
        fw.flush();
        fw.close();

    }
}
