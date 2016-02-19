package com.steelzack.xmladder;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.steelzack.xmladder.csv.AttributeBean;
import com.steelzack.xmladder.instruction.XmlAdderAddAttributeManager;
import com.steelzack.xmladder.instruction.XmlAdderInstruction;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by joaofilipesabinoesperancinha on 16-02-16.
 */
public class XmlAdderManager {

    // Document parsing setup instances
    private final DocumentBuilderFactory factory =
            DocumentBuilderFactory.newInstance();
    private DocumentBuilder builder = null;
    private final XPathFactory xPathfactory = XPathFactory.newInstance();
    private final XPath xpath = xPathfactory.newXPath();
    final String rule;
    private XmlAdderAddAttributeManager addAttributeManager = new XmlAdderAddAttributeManager();

    private final File fileSourceDirectory;
    private final File fileDestinationDirectory;

    public XmlAdderManager( //
                            File fileSourceDirectory,
                            File fileDestinationDirectory, //
                            InputStream fileAddAttributes, //
                            InputStream fileRule) throws IOException, ParserConfigurationException {
        this.fileSourceDirectory = fileSourceDirectory;
        this.fileDestinationDirectory = fileDestinationDirectory;
        rule = IOUtils.toString(fileRule);
        factory.setNamespaceAware(false);
        builder = factory.newDocumentBuilder();
        readAllAddAttributes(fileAddAttributes);
    }

    protected void readAllAddAttributes(InputStream fileAddAttributes) throws IOException {
        final HeaderColumnNameMappingStrategy<AttributeBean> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(AttributeBean.class);
        final CsvToBean<AttributeBean> csvToBean = new CsvToBean<>();
        final List<AttributeBean> beanList = csvToBean.parse(strategy, new InputStreamReader(fileAddAttributes));

        for (AttributeBean attBean : beanList) {
            addAttributeManager.addAttribute(attBean.getName(), attBean.getValue(), attBean.getXpath());
        }
    }

    public XmlAdderAddAttributeManager getAddAttributeManager() {
        return addAttributeManager;
    }

    protected static List<File> listAllFilesToChange(final File sourceDirectory) {
        final List<File> allXmlsToChahge = new ArrayList<>();
        final File[] fileList = sourceDirectory.listFiles();
        for (final File f : fileList) {
            if (f.isDirectory()) {
                allXmlsToChahge.addAll(listAllFilesToChange(f));
            } else if (f.isFile()) {
                if (f.getName().endsWith("xml")) {
                    allXmlsToChahge.add(f);
                }
            }
        }
        return allXmlsToChahge;
    }

    protected void runConversion() throws IOException, SAXException, XPathExpressionException, TransformerException {
        final List<File> allXmlFilesToChange = listAllFilesToChange(fileSourceDirectory);
        for (final File file : allXmlFilesToChange) {
            final Document doc = getDocument(file);
            for (String xpathString : addAttributeManager.getXmlAdderInstructionArrayMap().keySet()) {
                final XmlAdderInstruction instruction = addAttributeManager.getXmlAdderInstructionArrayMap().get(xpathString);
                final XPathExpression expr = xpath.compile(xpathString);
                final NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
                for (int i = 0; i < nl.getLength(); i++) {
                    final Node node = nl.item(i);
                    final Map<String, String> attributesToAdd = instruction.getAttributesToAdd();
                    for (String attName : attributesToAdd.keySet()) {
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            final String value = attributesToAdd.get(attName);
                            if (value == null || value.isEmpty()) {
                                ((Element) node).setAttribute(attName, getRule());
                            } else {
                                ((Element) node).setAttribute(attName, value);
                            }
                        }
                    }
                }
            }
            saveFile(file, doc);
        }
    }

    protected void saveFile(final File file, final Document doc) throws TransformerException {
        final String absolutePath = file.getAbsolutePath();
        final String rootSourceFolder = fileSourceDirectory.getAbsolutePath();
        final String rootDestinationFolder = fileDestinationDirectory //
                .getAbsolutePath() //
                .concat(absolutePath.replace(rootSourceFolder, ""));

        final File destinationFile = new File(rootDestinationFolder);
        new File(destinationFile.getParent()).mkdirs();

        final Transformer transformer = TransformerFactory.newInstance().newTransformer();
        final Result output = new StreamResult(destinationFile);
        final Source input = new DOMSource(doc);

        transformer.transform(input, output);
    }

    protected Document getDocument(File f) throws IOException, SAXException {
        final FileInputStream is = new FileInputStream(f);
        final Document doc = builder.parse(is);
        is.close();
        return doc;
    }

    protected String getRule() throws IOException {
        return rule //
                .replace("\\\\", "\\") //
                .replace("\\GUID", "\\GTRANSIT") //
                .replace("+GUID", UUID.randomUUID().toString().toUpperCase()) //
                .replace("GUID",UUID.randomUUID().toString()) //
                .replace("\\GTRANSIT","GUID"); //
    }
}
