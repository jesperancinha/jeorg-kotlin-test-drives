package com.steelzack.coffee.system.manager;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
public class GeneralProcessorImplTest {

    @Test
    public void startSimulationProcess() throws Exception {
        final InputStream testMachinesFile = getClass().getResourceAsStream("/coffemachine_example_test_1.xml");
        final InputStream testEmployeesFile = getClass().getResourceAsStream("/employees_example_test_1.xml");
        final GeneralProcessor generalProcessor = GeneralProcessorImpl.builder().nIterations(1).build();

        generalProcessor.startSimulationProcess(
                testMachinesFile, //
                testEmployeesFile
        ); //
    }

}