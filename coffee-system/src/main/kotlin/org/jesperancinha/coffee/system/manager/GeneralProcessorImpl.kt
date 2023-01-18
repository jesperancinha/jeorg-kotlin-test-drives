package org.jesperancinha.coffee.system.manager

import org.jesperancinha.coffee.system.objects.EmployeeLayer
import jakarta.xml.bind.JAXBContext
import jakarta.xml.bind.JAXBException
import lombok.*
import lombok.experimental.Accessors
import lombok.extern.slf4j.Slf4j
import org.jesperancinha.coffee.system.input.CoffeeMachines
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.*
import java.util.function.Consumer

@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Service
@Slf4j
class GeneralProcessorImpl {
    private val nIterations = 0
    private val sourceXmlMachinesFile: String? = null
    private val sourceXmlEmployeesFile: String? = null
    private val preRowSize = 0
    private val postRowSize = 0
    private var coffeeMachines: CoffeeMachines? = null
    private var employees: Employees? = null

    @Autowired
    private val machineProcessor: MachineProcessorImpl? = null

    /**
     * Creates the coffee machines from the XML file
     *
     * @param sourceXmlCoffeeMachineFile
     * @return CoffeeMachines list
     */
    @Throws(JAXBException::class)
    private fun createCoffeeMachines(sourceXmlCoffeeMachineFile: InputStream): CoffeeMachines {
        val jaxbContext: JAXBContext = JAXBContext.newInstance(CoffeeMachines::class.java)
        val unmarshaller = jaxbContext.createUnmarshaller()
        return unmarshaller.unmarshal(sourceXmlCoffeeMachineFile) as CoffeeMachines
    }

    /**
     * Creates the employess from the XML file
     *
     * @param sourceXmlEmployeesFile
     * @return Employees list
     */
    @Throws(JAXBException::class)
    private fun createEmployees(sourceXmlEmployeesFile: InputStream): Employees {
        val jaxbContext: JAXBContext = JAXBContext.newInstance(Employees::class.java)
        val unmarshaller = jaxbContext.createUnmarshaller()
        return unmarshaller.unmarshal(sourceXmlEmployeesFile) as Employees
    }

    /**
     * Starts the simulation
     */
    @Throws(FileNotFoundException::class, JAXBException::class)
    fun initSimulationProcess() {
        GeneralProcessorImpl.log.info("Starting coffee simulation process...")
        initSimulationProcess(
            FileInputStream(
                sourceXmlMachinesFile
            ),
            FileInputStream(
                sourceXmlEmployeesFile
            )
        )
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
    @Throws(JAXBException::class)
    fun initSimulationProcess(coffeesFile: InputStream, employeesFile: InputStream) {
        GeneralProcessorImpl.log.info("Starting simulation process...")
        coffeeMachines = createCoffeeMachines(coffeesFile)
        employees = createEmployees(employeesFile)
    }

    fun start() {
        val preProcessor = machineProcessor.getPreProcessor()
        preProcessor.addQueueSize(preRowSize, MAIN_QUEUE_PRE)
        preProcessor.initExecutors()
        val postProcessor = machineProcessor.getPostProcessor()
        postProcessor.addQueueSize(postRowSize, MAIN_QUEUE_POST)
        postProcessor.initExecutors()
        val nMachines: Int = coffeeMachines.getCoffeMachine().size
        val employeeLayerList: MutableList<EmployeeLayer> = ArrayList()
        val random = Random()
        fillEmployeeLayerList(nMachines, employeeLayerList, random)
        startCoffeeMeeting(employeeLayerList)
        runaAllProcessors()
        waitForAllProcessors()
        stopAllProcessors()
    }

    private fun fillEmployeeLayerList(nMachines: Int, employeeLayerList: MutableList<EmployeeLayer>, random: Random) {
        val coffeeProcessor = machineProcessor.getCoffeeProcessor()
        val paymentProcessor = machineProcessor.getPaymentProcessor()
        employees.getEmployee().forEach(
            Consumer<Employee> { employee: Employee? ->
                val iChosenCoffeeMachine = random.nextInt(nMachines)
                val coffeMachine: CoffeMachine = coffeeMachines.getCoffeMachine()
                    .get(iChosenCoffeeMachine)
                val nCoffees: Int = coffeMachine.getCoffees().getCoffee().size
                val iCoffee = random.nextInt(nCoffees)
                val nPayments: Int = coffeMachine.getPaymentTypes().getPayment().size
                val iPayment = random.nextInt(nPayments)
                val chosenCoffee: Coffee = coffeMachine.getCoffees().getCoffee().get(iCoffee)
                val chosenPayment: Payment = coffeMachine.getPaymentTypes().getPayment().get(iPayment)
                coffeeProcessor.addQueueSize(1, chosenCoffee.getName())
                paymentProcessor.addQueueSize(1, chosenPayment.getName())
                val employeeLayer = EmployeeLayer.builder()
                    .employee(employee)
                    .coffee(chosenCoffee)
                    .payment(chosenPayment)
                    .build()
                employeeLayerList.add(employeeLayer)
            }
        )
        coffeeProcessor.initExecutors()
        paymentProcessor.initExecutors()
    }

    private fun startCoffeeMeeting(employeeLayerList: List<EmployeeLayer>) {
        employeeLayerList.forEach(
            Consumer { employeeLayer: EmployeeLayer ->
                val employee: Employee = employeeLayer.employee
                val preActions: List<org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PreAction?> =
                    employee.getActions().getPreAction()
                val coffee: Coffee = employeeLayer.coffee
                val payment: Payment = employeeLayer.payment
                val postActions: List<PostAction?> = employee.getActions().getPostAction()
                machineProcessor!!.callPreActions(employee, MAIN_QUEUE_PRE, preActions, coffee, payment, postActions)
            }
        )
    }

    private fun runaAllProcessors() {
        machineProcessor.getPreProcessor().runAllCalls()
        // machineProcessor.getCoffeeProcessor().runAllCalls();
        // machineProcessor.getPaymentProcessor().runAllCalls();
        // machineProcessor.getPostProcessor().runAllCalls();
    }

    private fun waitForAllProcessors() {
        machineProcessor.getPreProcessor().waitForAllCalls()
        // machineProcessor.getCoffeeProcessor().waitForAllCalls();
        // machineProcessor.getPaymentProcessor().waitForAllCalls();
        // machineProcessor.getPostProcessor().waitForAllCalls();
    }

    private fun stopAllProcessors() {
        machineProcessor.getPreProcessor().stopExectutors()
        //  machineProcessor.getCoffeÒØeProcessor().stopExectutors();
        //  machineProcessor.getPaymentProcessor().stopExectutors();
        //  machineProcessor.getPostProcessor().stopExectutors();
    }

    companion object {
        const val MAIN_QUEUE_PRE = "MAIN_QUEUE_PRE"
        const val MAIN_QUEUE_POST = "MAIN_QUEUE_POST"
    }
}