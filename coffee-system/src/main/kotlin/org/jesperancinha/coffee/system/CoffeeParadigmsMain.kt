package com.jesperancinha.coffee.system;

import jakarta.xml.bind.JAXBException;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.FileNotFoundException;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
public class CoffeeParadigmsMain {
    public static void main(String[] args) throws
            CmdLineException,
            FileNotFoundException,
            JAXBException {
        final CoffeParadigmsOptions options = new CoffeParadigmsOptions();
        final CmdLineParser parser = new CmdLineParser(options);
        parser.parseArgument(args);
        options.run();
    }
}
