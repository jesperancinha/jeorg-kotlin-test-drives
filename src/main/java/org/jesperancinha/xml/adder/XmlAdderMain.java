package org.jesperancinha.xml.adder;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XmlAdderMain {

    public static void main(String[] args) throws IOException, ParserConfigurationException {
        final String sourceDirectory = args[0];
        final String destinationDirectory = args[1];
        final String addAttributesFile = args[2];
        final String deleteAttributesFile = args[3];
        final String rule = args[4];

        final File fileSourceDirectory = new File(sourceDirectory);
        final File fileDestinationDirectory = new File(destinationDirectory);
        final InputStream fileAddAtributesFile = new FileInputStream(new File(addAttributesFile));
        final InputStream fileDeleteAttributesFile = new FileInputStream(new File(deleteAttributesFile)) ;
        final InputStream fileRule = new FileInputStream(new File(rule));

        final XmlAdderManager manager = new XmlAdderManager( //
                fileSourceDirectory, //
                fileDestinationDirectory, //
                fileAddAtributesFile, //
                fileDeleteAttributesFile, //
                fileRule);
    }
}
