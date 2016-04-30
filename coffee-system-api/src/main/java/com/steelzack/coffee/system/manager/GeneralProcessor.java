package com.steelzack.coffee.system.manager;

import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
interface GeneralProcessor {
    void startSimulationProcess() //
    throws FileNotFoundException, //
            JAXBException, //
            SAXException; //

    void startSimulationProcess(InputStream coffeesFile, InputStream employeesFile) //
    throws FileNotFoundException, //
            JAXBException, //
            SAXException; //
}
