package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.CoffeeMachines;
import com.steelzack.coffee.system.input.Employees;
import lombok.Builder;
import lombok.Getter;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
@Builder
@Getter
public class GeneralProcessorImpl implements GeneralProcessor {

    final int nIterations;
    final String sourceXmlMachinesFile;
    final String sourceXmlEmployeesFile;

    private CoffeeMachines coffeMachines;
    private Employees employes;

    /**
     * Creates the coffee machines from the XML file
     *
     * @param sourceXmlCoffeeMachineFile
     * @return CoffeeMachines list
     */
    private CoffeeMachines createCoffeeMachines(final InputStream sourceXmlCoffeeMachineFile)
            throws //
            JAXBException, //
            SAXException, //
            FileNotFoundException { //
        final JAXBContext jaxbContext = JAXBContext.newInstance("com.steelzack.coffee.system.input");
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (CoffeeMachines) unmarshaller.unmarshal(sourceXmlCoffeeMachineFile);
    }

    /**
     * Creates the employess from the XML file
     *
     * @param sourceXmlEmployeesFile
     * @return Employees list
     */
    private Employees createEmployees(final InputStream sourceXmlEmployeesFile)
            throws //
            JAXBException, //
            SAXException, //
            FileNotFoundException { //
        final JAXBContext jaxbContext = JAXBContext.newInstance("com.steelzack.coffee.system.input");
        final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (Employees) unmarshaller.unmarshal(sourceXmlEmployeesFile);

    }

    /**
     * Starts the simulation
     */
    @Override
    public void startSimulationProcess()
            throws FileNotFoundException, //
            JAXBException, //
            SAXException //
    { //
        startSimulationProcess( //
                new FileInputStream( //
                        this.sourceXmlMachinesFile //
                ), //
                new FileInputStream( //
                        this.sourceXmlEmployeesFile //
                ) //
        ); //
    }

    /**
     * Starts simulation using inputStreams
     *
     * @param coffeesFile
     * @param employeesFile
     * @throws FileNotFoundException
     * @throws JAXBException
     * @throws SAXException
     */
    @Override
    public void startSimulationProcess( //
                                        InputStream coffeesFile, //
                                        InputStream employeesFile //
    ) //
            throws //
            FileNotFoundException, //
            JAXBException, //
            SAXException //
    {
        this.coffeMachines = createCoffeeMachines(coffeesFile);
        this.employes = createEmployees(employeesFile);
    }
}
