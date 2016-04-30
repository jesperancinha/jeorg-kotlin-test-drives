package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.CoffeeMachines;
import com.steelzack.coffee.system.input.Employees;
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

    CoffeeMachines getCoffeeMachines();

    Employees getEmployees();

}
