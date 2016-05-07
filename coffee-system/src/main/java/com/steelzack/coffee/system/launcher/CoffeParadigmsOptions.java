package com.steelzack.coffee.system.launcher;

import com.steelzack.coffee.system.manager.GeneralProcessorImpl;
import org.kohsuke.args4j.Option;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

/**
 * Created by joao on 29-4-16.
 */
@Component
public class CoffeParadigmsOptions {

    @Option(name = "-it", usage = "Define the number of iterations you want to make", aliases = "--iterations", required = true)
    private Integer nIterations = 1;

    @Option(name = "-ud", usage = "Defines where the user definition file is", aliases = "--userdefinitions", required = true)
    private String userDefinitionFile;

    @Option(name = "-md", usage = "Defines where the machine definition file is", aliases = "--machinedefinitions", required = true)
    private String machineDefinitionFile;

    @Option(name = "-pre", usage = "Defines how many can stand on the pre-actions queue", aliases = "--pre-actions-size", required = true)
    private Integer nPreActions;

    @Option(name = "-post", usage = "Defines how many can stand on the post-actions queue", aliases = "--post-actions-size", required = true)
    private Integer nPostActions;

    public void run() throws FileNotFoundException, JAXBException, SAXException, InterruptedException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("META-INF/config.xml");
        GeneralProcessorImpl generalProcessor = context.getBean(GeneralProcessorImpl.class);
        generalProcessor.setNIterations(nIterations);
        generalProcessor.setSourceXmlEmployeesFile(userDefinitionFile);
        generalProcessor.setSourceXmlMachinesFile(machineDefinitionFile);
        generalProcessor.setPreRowSize(nPreActions);
        generalProcessor.setPostRowSize(nPostActions);
        generalProcessor.initSimulationProcess();
        generalProcessor.start();
    }
}
