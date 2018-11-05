package com.jesperancinha.xmladder;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.jesperancinha.xmladder.instruction.XmlAdderAddAttributeManager;
import com.jesperancinha.xmladder.instruction.XmlAdderInstruction;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.xml.sax.SAXException;

/**
 * Created by joaofilipesabinoesperancinha on 18-02-16.
 */
public class XmlAdderManagerTest {
	private static final String guidMatch = "([0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12})";
	private static final Mockery context = new Mockery();

	@Before
	public void setupMocks() {
		context.setImposteriser(ClassImposteriser.INSTANCE);
	}

	@Test
	public void testReadAllAddAttributes() throws IOException, ParserConfigurationException {
		final InputStream is = getClass().getResourceAsStream("testReadAttributeBean.csv");
		final InputStream ruleStream = context.mock(FileInputStream.class);
		final XmlAdderManager manager = new XmlAdderManager(null, null, is, null, ruleStream) {
			@Override
			protected String getRuleFromIO(InputStream fileRule) {
				return null;
			}
		};

		final XmlAdderAddAttributeManager attributeManager = manager.getAddAttributeManager();
		final Map<String, XmlAdderInstruction> result = attributeManager.getXmlAdderInstructionArrayMap();
		final Set<String> resultSet = result.keySet();

		assertEquals(1, resultSet.size());
		for (String key : resultSet) {
			assertEquals("testnode1/testnode2", key);
			assertEquals("attribute1value", result.get(key).getAttributesToAdd().get("attribute1name"));
		}
	}

	@Test
	public void testCompleteProcess() throws SAXException, TransformerException, XPathExpressionException, IOException,
			ParserConfigurationException {
		final InputStream inputStreamRule = getClass().getResourceAsStream("testDesc.txt");
		final InputStream inputStreamAttributeBean = getClass().getResourceAsStream("testDescBean.csv");
		final File testFolder = getTestFolder(true);
		final XmlAdderManager manager = new XmlAdderManager( //
				testFolder, //
				new File("/tmp"), //
				inputStreamAttributeBean, //
				null, inputStreamRule //
		);

		final XmlAdderAddAttributeManager attributeManager = manager.getAddAttributeManager();
		final Map<String, XmlAdderInstruction> result = attributeManager.getXmlAdderInstructionArrayMap();
		final Set<String> resultSet = result.keySet();

		assertEquals(1, resultSet.size());
		for (String key : resultSet) {
			assertEquals("testnode1/testnode2", key);
			assertEquals(null, result.get(key).getAttributesToAdd().get("attribute1name"));
		}
		manager.runConversion();
	}

	@Test
	public void testlistAllFilesToChange_empty() throws IOException {
		final List<File> resultFilesToChange = XmlAdderManager.listAllFilesToChange(getTestFolder(false));

		assertEquals(0, resultFilesToChange.size());
	}

	@Test
	public void testlistAllFilesToChange_oneXmlInEachFolder() throws IOException {
		final java.util.List<File> resultFilesToChange = XmlAdderManager.listAllFilesToChange(getTestFolder(true));

		assertThat(resultFilesToChange, hasSize(6));
		assertThat(resultFilesToChange.stream().map(File::getName).collect(Collectors.toList()),
				hasItems("file1.xml", "file11.xml", "file12.xml", "file2.xml", "file21.xml", "file22.xml"));
	}

	@Test
	public void testGetRule() throws IOException, ParserConfigurationException {
		testGetRule("testRule0.txt", "I am a", true, false);
		testGetRule("testRule0.1.txt", "I am a", true, false);
		testGetRule("testRule1.txt", "I am not a GUID", false, false);
		testGetRule("testRule2.txt", "I am a slash behind a \\", true, false);
		testGetRule("testRule3.txt", "I am two slashes \\\\", true, false);
		testGetRule("testRule4.txt", "I am two slashes and not a \\\\GUID", false, false);
	}

	public void testGetRule(String testRule, String testRuleMatch, boolean testGUID, boolean testUpperCase)
			throws IOException, ParserConfigurationException {
		final XmlAdderManager manager = new XmlAdderManager( //
				null, //
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
			final Pattern pattern = Pattern.compile(guidMatch);
			final Matcher matcher = pattern.matcher(resultRule);
			try {
				Assert.assertTrue(matcher.find());
			} catch (Error e) {
				throw new RuntimeException("Match GUUID not found! Got: " + resultRule, e);
			}
			String resultMatch = resultRule.replace(matcher.group(0), "").trim();
			if (testUpperCase) {
				resultMatch = resultMatch.toUpperCase();
			}
			assertEquals(testRuleMatch, resultMatch);
		} else {
			assertEquals(testRuleMatch, resultRule);
		}
	}

	private File getTestFolder(boolean addFiles) throws IOException {
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

	private void createFile(File folder, String fileName, String content) throws IOException {
		final File fileElement = new File(folder, fileName + ".xml");
		fileElement.deleteOnExit();
		fileElement.createNewFile();
		final FileWriter fw = new FileWriter(fileElement);
		fw.write(content);
		fw.flush();
		fw.close();

	}
}
