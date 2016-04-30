package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.CoffeeMachines;
import com.steelzack.coffee.system.input.Employees;
import lombok.Builder;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
@Builder
public class GeneralProcessorImpl implements GeneralProcessor {

    /**
     * Creates the coffee machines from the XML file
     *
     * @param sourceXmlCoffeeMachineFile
     * @return CoffeeMachines list
     */
    private CoffeeMachines createCoffeeMachines(final String sourceXmlCoffeeMachineFile) {
        return null;
    }

    /**
     * Creates the employess from the XML file
     * @param sourceXmlEmployeesFile
     * @return Employees list
     */
    private Employees createEmployees(final String sourceXmlEmployeesFile) {
        return  null;
    }

    /**
     * Starts the simulation
     * @param nIterations
     * @param sourceXmlcoffeesFile
     * @param sourceXmlmachinesFile
     */
    @Override
    public void startSimulationProcess(int nIterations, String sourceXmlcoffeesFile, String sourceXmlmachinesFile) {

    }
}
