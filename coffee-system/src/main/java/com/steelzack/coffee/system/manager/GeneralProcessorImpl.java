package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.CoffeeMachines;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.steelzack.coffee.system.input.Employees;
import com.steelzack.coffee.system.input.Employees.Employee;
import com.steelzack.coffee.system.objects.EmployeeLayer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Service
public class GeneralProcessorImpl implements GeneralProcessor {

    public static final String MAIN_QUEUE = "MAIN_QUEUE";
    int nIterations;
    String sourceXmlMachinesFile;
    String sourceXmlEmployeesFile;
    int preRowSize;
    int postRowSize;

    private CoffeeMachines coffeeMachines;
    private Employees employees;

    private CoffeeProcessor coffeeProcessor;
    private PaymentProcessor paymentProcessor;

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
        this.coffeeProcessor = machineProcessor.getCoffeeProcessor();
        this.paymentProcessor = machineProcessor.getPaymentProcessor();
    }

    @Override
    public void start() throws InterruptedException {
        final EmployeeProcessor employeeProcessor = machineProcessor.getEmployeeProcessor();
        employeeProcessor.addQueueSize(preRowSize, MAIN_QUEUE);
        employeeProcessor.setPostQueueSize(postRowSize, MAIN_QUEUE);
        final int nMachines = coffeeMachines.getCoffeMachine().size();
        final List<EmployeeLayer> employeeLayerList = new ArrayList<>();
        final Random random = new Random();

        fillEmployeeLayerList(nMachines, employeeLayerList, random);
        startCoffeeMeeting(employeeProcessor, employeeLayerList);
    }

    private void fillEmployeeLayerList(int nMachines, List<EmployeeLayer> employeeLayerList, Random random) {
        this.employees.getEmployee().stream().forEach(
                employee -> {
                    final int iChosenCoffeeMachine = random.nextInt(nMachines);
                    final CoffeeMachines.CoffeMachine coffeMachine = coffeeMachines.getCoffeMachine().get(iChosenCoffeeMachine);
                    final int nCoffees = coffeMachine.getCoffees().getCoffee().size();
                    final int iCoffee = random.nextInt(nCoffees);
                    final int nPayments = coffeMachine.getPaymentTypes().getPayment().size();
                    final int iPayment = random.nextInt(nPayments);
                    final Coffee chosenCoffee = coffeMachine.getCoffees().getCoffee().get(iCoffee);
                    final Payment chosenPayment = coffeMachine.getPaymentTypes().getPayment().get(iPayment);

                    coffeeProcessor.addQueueSize(1, chosenCoffee.getName());
                    paymentProcessor.addQueueSize(1, chosenPayment.getName());

                    final EmployeeLayer employeeLayer = EmployeeLayer //
                            .builder() //
                            .employee(employee) //
                            .coffee(chosenCoffee) //
                            .payment(chosenPayment) //
                            .build(); //
                    employeeLayerList.add(employeeLayer);
                }
        );
    }

    private void startCoffeeMeeting(EmployeeProcessor employeeProcessor, List<EmployeeLayer> employeeLayerList) {
        employeeLayerList.stream().forEach(
                employeeLayer -> {
                    final Employee employee = employeeLayer.getEmployee();
                    employeeProcessor.setActions(employee.getActions());
                    final Coffee chosenCoffee = employeeLayer.getCoffee();
                    coffeeProcessor.setChosenCoffee(
                            chosenCoffee //
                    );
                    final Payment chosenPayment = employeeLayer.getPayment();
                    paymentProcessor.setChosenPayment(
                            chosenPayment //
                    );
                    machineProcessor.callPreActions(MAIN_QUEUE);
                    machineProcessor.callMakeCoffee(chosenCoffee.getName());
                    machineProcessor.callPayCoffee(chosenPayment.getName());
                    machineProcessor.callPostActions(MAIN_QUEUE);
                }
        );
    }

}
