package com.jesperancinha.coffee.api.manager;

import com.jesperancinha.coffee.system.input.CoffeeMachines;
import com.jesperancinha.coffee.system.input.Employees;
import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.InputStream;

public interface GeneralProcessor {
    CoffeeMachines getCoffeeMachines();

    Employees getEmployees();

    void initSimulationProcess()
            throws FileNotFoundException,
            SAXException, JAXBException;

    void initSimulationProcess(
            InputStream coffeesFile,
            InputStream employeesFile
    )
            throws
            FileNotFoundException,
            JAXBException,
            SAXException;

    void start() throws InterruptedException;
}
