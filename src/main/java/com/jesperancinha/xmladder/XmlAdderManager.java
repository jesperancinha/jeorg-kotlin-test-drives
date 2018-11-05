package com.jesperancinha.xmladder;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.jesperancinha.xmladder.csv.AttributeAddBean;
import com.jesperancinha.xmladder.csv.AttributeDeleteBean;
import com.jesperancinha.xmladder.instruction.XmlAdderAddAttributeManager;
import com.jesperancinha.xmladder.instruction.XmlAdderInstruction;
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
import java.util.*;

/**
 * Created by joaofilipesabinoesperancinha on 16-02-16.
 */
public class XmlAdderManager {

    // Constants
    public static final String GUID = "GUID";
    public static final String GUID_PLUS = "+GUID";
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
                            InputStream fileDeleteAttributes, //
                            InputStream fileRule //
    ) //
            throws IOException, ParserConfigurationException {
        this.fileSourceDirectory = fileSourceDirectory;
        this.fileDestinationDirectory = fileDestinationDirectory;
        this.rule = getRuleFromIO(fileRule);
        this.factory.setNamespaceAware(true);
        this.builder = factory.newDocumentBuilder();
        if (fileAddAttributes != null) {
            readAllAddAttributes(fileAddAttributes);
        }
        if (fileDeleteAttributes != null) {
            readAllDeleteAttributes(fileDeleteAttributes);
        }
    }

    private void readAllDeleteAttributes(InputStream fileDeleteAttributes) {
        final HeaderColumnNameMappingStrategy<AttributeDeleteBean> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(AttributeDeleteBean.class);
        final CsvToBean<AttributeDeleteBean> csvToBean = new CsvToBean<>();
        final List<AttributeDeleteBean> beanList = csvToBean.parse(strategy, new InputStreamReader(fileDeleteAttributes));

        for (AttributeDeleteBean attBean : beanList) {
            addAttributeManager.addRemoveAttribute(attBean.getName(), attBean.getXpath());
        }
    }

    protected String getRuleFromIO(InputStream fileRule) throws IOException {
        return IOUtils.toString(fileRule);
    }

    protected void readAllAddAttributes(InputStream fileAddAttributes) throws IOException {
        final HeaderColumnNameMappingStrategy<AttributeAddBean> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(AttributeAddBean.class);
        final CsvToBean<AttributeAddBean> csvToBean = new CsvToBean<>();
        final List<AttributeAddBean> beanList = csvToBean.parse(strategy, new InputStreamReader(fileAddAttributes));

        for (AttributeAddBean attBean : beanList) {
            addAttributeManager.addAddAttribute(attBean.getName(), attBean.getValue(), attBean.getXpath());
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
            boolean saveFile = false;
            for (String xpathString : addAttributeManager.getXmlAdderInstructionArrayMap().keySet()) {
                final XmlAdderInstruction instruction = addAttributeManager.getXmlAdderInstructionArrayMap().get(xpathString);
                final XPathExpression expr = xpath.compile(xpathString);
                final NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
                for (int i = 0; i < nl.getLength(); i++) {
                    final Node node = nl.item(i);
                    final Map<String, String> attributesToAdd = instruction.getAttributesToAdd();
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        for (String attName : attributesToAdd.keySet()) {
                            final String value = attributesToAdd.get(attName);
                            if (value == null || value.isEmpty()) {
                                ((Element) node).setAttribute(attName, getRule());
                            } else {
                                ((Element) node).setAttribute(attName, value);
                            }
                            saveFile = true;
                        }

                        final Set<String> attributesToDelete = instruction.getAttributeKeysToDelete();
                        for (String attName : attributesToDelete) {
                            if (attName != null && //
                                    ((Element) node).getAttribute(attName) != null && //
                                    !((Element) node).getAttribute(attName).isEmpty() //
                                    ) //
                            {
                                ((Element) node).removeAttribute(attName);
                                saveFile = true;
                            }
                        }
                    }
                }
            }
            if (saveFile) {
                saveFile(file, doc);
            }
        }
    }

    protected void saveFile(final File file, final Document doc) throws TransformerException {
        final String absolutePath = file.getAbsolutePath();
        final String rootSourceFolder = fileSourceDirectory.getAbsolutePath();
        final String rootDestinationFolder = fileDestinationDirectory //
                .getAbsolutePath() //
                .concat("/") //
                .concat(fileSourceDirectory.getName()) //
                .concat(absolutePath.replace(rootSourceFolder, ""));

        final File destinationFile = new File(rootDestinationFolder);
        new File(destinationFile.getParent()).mkdirs();

        final Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

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
        final String[] slashPiece = rule.split("\\\\");
        final int slashLength = slashPiece.length;

        int slashCounter = 0;

        final StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < slashLength; i++) {
            final String currentSlash = slashPiece[i];
            if (currentSlash.equals("")) {
                slashCounter++;
                if (slashCounter == 2) {
                    buffer.append("\\");
                    slashCounter = 0;
                }
            } else if (currentSlash.startsWith(GUID) && slashCounter == 1) {
                buffer.append(currentSlash);
            } else {
                slashCounter = 1;
                if (currentSlash.contains(GUID_PLUS)) {
                    buffer.append(currentSlash.replace(GUID_PLUS, UUID.randomUUID().toString().toUpperCase()));
                } else if (currentSlash.contains(GUID)) {
                    buffer.append(currentSlash.replace(GUID, UUID.randomUUID().toString()));
                } else {
                    buffer.append(currentSlash);
                }
            }
        }

        return buffer.toString();
    }
}
