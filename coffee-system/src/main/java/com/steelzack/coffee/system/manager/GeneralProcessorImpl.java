package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.CoffeeMachines;
import com.steelzack.coffee.system.input.Employees;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
@Accessors(chain = true)
@Builder
@Getter
@Service
public class GeneralProcessorImpl implements GeneralProcessor {

    final int nIterations;
    final String sourceXmlMachinesFile;
    final String sourceXmlEmployeesFile;
    final int preRowSize;
    final int postRowSize;

    private CoffeeMachines coffeeMachines;
    private Employees employees;

    @Autowired
    private MachineProcessor machineProcessor;

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
    public void initSimulationProcess()
            throws FileNotFoundException, //
            JAXBException, //
            SAXException //
    { //
        initSimulationProcess( //
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
    public void initSimulationProcess( //
                                       InputStream coffeesFile, //
                                       InputStream employeesFile //
    ) //
            throws //
            FileNotFoundException, //
            JAXBException, //
            SAXException //
    {
        this.coffeeMachines = createCoffeeMachines(coffeesFile);
        this.employees = createEmployees(employeesFile);
    }

    @Override
    public void start() throws InterruptedException {
        final EmployeeProcessor employeeProcessor = machineProcessor.getEmployeeProcessor();
        employeeProcessor.setQueueSize(preRowSize);
        employeeProcessor.setPostQueueSize(postRowSize);
        final int nMachines = coffeeMachines.getCoffeMachine().size();
        final CoffeeProcessor coffeeProcessor = machineProcessor.getCoffeeProcessor();
        coffeeProcessor.setQueueSize(nMachines);
        final PaymentProcessor paymentProcessor = machineProcessor.getPaymentProcessor();
        paymentProcessor.setQueueSize(nMachines);

        this.employees.getEmployee().stream().forEach(
                employee -> {
                    employeeProcessor.setActions(employee.getActions());
                    int iChosenCoffeeMachine = 0;
                    int iChosenCoffee = 0;
                    int nChosenPayment = 0;
                    final CoffeeMachines.CoffeMachine coffeMachine = this.coffeeMachines //
                            .getCoffeMachine() //
                            .get(iChosenCoffeeMachine);

                    coffeeProcessor.setChosenCoffee(
                            coffeMachine //
                                    .getCoffees() //
                                    .getCoffee() //
                                    .get(iChosenCoffee) //
                    );
                    paymentProcessor.setChosenPayment(
                            coffeMachine
                            .getPaymentTypes()
                            .getPayment()
                            .get(nChosenPayment)
                    );

                    machineProcessor.callPreActions();
                    machineProcessor.callMakeCoffee();
                    machineProcessor.callPayCoffee();
                    machineProcessor.callPostActions();
                }
        );
    }


}
