package com.steelzack.coffee.system.launcher;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
public class CoffeeParadigmsMain {
    public static void main(String[] args) throws
            CmdLineException, //
            InterruptedException, //
            FileNotFoundException, //
            SAXException, //
            JAXBException {
        final CoffeParadigmsOptions options = new CoffeParadigmsOptions();
        final CmdLineParser parser = new CmdLineParser(options);
        parser.parseArgument(args);
        options.run();
    }
}
