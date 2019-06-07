package com.jesperancinha.coffee.system.manager;

import com.jesperancinha.coffee.system.input.CoffeeMachines;
import com.jesperancinha.coffee.system.input.Employees;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
public interface GeneralProcessor {
    CoffeeMachines getCoffeeMachines();

    Employees getEmployees();

    void initSimulationProcess()
            throws FileNotFoundException, //
            JAXBException, //
            SAXException //
    ;

    void initSimulationProcess( //
                                InputStream coffeesFile, //
                                InputStream employeesFile //
    ) //
            throws //
            FileNotFoundException, //
            JAXBException, //
            SAXException //
    ;

    void start() throws InterruptedException;
}
