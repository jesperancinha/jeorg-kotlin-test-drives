package org.jesperancinha.coffee.system.manager

import jakarta.xml.bind.JAXBContext
import jakarta.xml.bind.JAXBException
import lombok.*
import lombok.experimental.Accessors
import org.jesperancinha.coffee.system.input.CoffeeMachines
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.objects.EmployeeLayer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.*

@Accessors(chain = true)
@Service
class GeneralProcessorImpl(
    @Autowired
    private val machineProcessor: MachineProcessorImpl
) {

    var nIterations: Int = 0
    var sourceXmlMachinesFile: String? = null
    var sourceXmlEmployeesFile: String? = null
    var preRowSize: Int = 0
    var postRowSize: Int = 0

    lateinit var coffeeMachines: CoffeeMachines
    lateinit var employees: Employees

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
        logger.info("Starting coffee simulation process...")
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
        logger.info("Starting simulation process...")
        coffeeMachines = createCoffeeMachines(coffeesFile)
        employees = createEmployees(employeesFile)
    }

    fun start() {
        val preProcessor = machineProcessor.preProcessor
        preProcessor.addQueueSize(preRowSize, MAIN_QUEUE_PRE)
        preProcessor.initExecutors()
        val postProcessor = machineProcessor.postProcessor
        postProcessor.addQueueSize(postRowSize, MAIN_QUEUE_POST)
        postProcessor.initExecutors()
        val nMachines: Int = coffeeMachines.coffeMachine.size
        val employeeLayerList: MutableList<EmployeeLayer> = ArrayList()
        val random = Random()
        fillEmployeeLayerList(nMachines, employeeLayerList, random)
        startCoffeeMeeting(employeeLayerList)
        runaAllProcessors()
        waitForAllProcessors()
        stopAllProcessors()
    }

    private fun fillEmployeeLayerList(nMachines: Int, employeeLayerList: MutableList<EmployeeLayer>, random: Random) {
        val coffeeProcessor = machineProcessor.coffeeProcessor
        val paymentProcessor = machineProcessor.paymentProcessor
        employees?.employee?.forEach { employee ->
            val iChosenCoffeeMachine = random.nextInt(nMachines)
            val coffeMachine: CoffeMachine = coffeeMachines.coffeMachine[iChosenCoffeeMachine]
            val nCoffees: Int = coffeMachine.coffees.coffee.size
            val iCoffee = random.nextInt(nCoffees)
            val nPayments: Int = coffeMachine.paymentTypes.payment.size
            val iPayment = random.nextInt(nPayments)
            val chosenCoffee: Coffee = coffeMachine.coffees.coffee[iCoffee]
            val chosenPayment: Payment = coffeMachine.paymentTypes.payment[iPayment]
            coffeeProcessor.addQueueSize(1, chosenCoffee.name)
            paymentProcessor.addQueueSize(1, chosenPayment.name)
            val employeeLayer = EmployeeLayer(
                employee = employee,
                coffee = chosenCoffee,
                payment = chosenPayment
            )
            employeeLayerList.add(employeeLayer)
        }
        coffeeProcessor.initExecutors()
        paymentProcessor.initExecutors()
    }

    private fun startCoffeeMeeting(employeeLayerList: List<EmployeeLayer>) {
        employeeLayerList.forEach { employeeLayer ->
            val employee = employeeLayer.employee
            val preActions: List<Employee.Actions.PreAction> =
                employee.actions.preAction
            val coffee: Coffee = employeeLayer.coffee
            val payment: Payment = employeeLayer.payment
            val postActions: List<PostAction?> = employee.actions.postAction
            machineProcessor.callPreActions(employee, MAIN_QUEUE_PRE, preActions, coffee, payment, postActions)
        }
    }

    private fun runaAllProcessors() {
        machineProcessor.preProcessor.runAllCalls()
        // machineProcessor.getCoffeeProcessor().runAllCalls();
        // machineProcessor.getPaymentProcessor().runAllCalls();
        // machineProcessor.postProcessor.runAllCalls();
    }

    private fun waitForAllProcessors() {
        machineProcessor.preProcessor.waitForAllCalls()
        // machineProcessor.getCoffeeProcessor().waitForAllCalls();
        // machineProcessor.getPaymentProcessor().waitForAllCalls();
        // machineProcessor.postProcessor.waitForAllCalls();
    }

    private fun stopAllProcessors() {
        machineProcessor.preProcessor.stopExecutors()
        //  machineProcessor.getCoffeÒØeProcessor().stopExectutors();
        //  machineProcessor.getPaymentProcessor().stopExectutors();
        //  machineProcessor.postProcessor.stopExectutors();
    }

    companion object {
        const val MAIN_QUEUE_PRE = "MAIN_QUEUE_PRE"
        const val MAIN_QUEUE_POST = "MAIN_QUEUE_POST"

        val logger: Logger = LoggerFactory.getLogger(GeneralProcessorImpl::class.java)
    }
}