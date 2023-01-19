package org.jesperancinha.coffee.system.manager

import com.google.common.truth.Truth
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.concurrency.CoffeeCallableImpl
import org.jesperancinha.coffee.system.concurrency.PaymentCallableImpl
import org.jesperancinha.coffee.system.concurrency.PostActionCallableImpl
import org.jesperancinha.coffee.system.concurrency.PreActionCallableImpl
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.Coffees.Coffee
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.Coffees.Coffee.TimesToFill.FillTime
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.queues.QueueCofeeImpl
import org.jesperancinha.coffee.system.queues.QueuePaymentImpl
import org.jesperancinha.coffee.system.queues.QueuePostActivityImpl
import org.jesperancinha.coffee.system.queues.QueuePreActivityImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import java.util.*
import java.util.concurrent.ExecutionException
import java.util.concurrent.Future
import java.util.concurrent.ThreadPoolExecutor

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
@ExtendWith(MockKExtension::class)
class GeneralProcessorImplTest {
    @InjectMockKs
    lateinit var generalProcessor: GeneralProcessorImpl

    @MockK(relaxed = true)
    lateinit var machineProcessor: MachineProcessorImpl

    @MockK(relaxed = true)
    lateinit var preProcessor: PreProcessorImpl

    @MockK(relaxed = true)
    lateinit var coffeeProcessor: CoffeeProcessorImpl

    @MockK(relaxed = true)
    lateinit var paymentProcessor: PaymentProcessorImpl

    @MockK(relaxed = true)
    lateinit var postProcessor: PostProcessorImpl

    @MockK(relaxed = true)
    lateinit var queueCoffee: QueueCofeeImpl

    @MockK
    lateinit var queuePayment: QueuePaymentImpl

    @MockK
    lateinit var queuePostActivity: QueuePostActivityImpl

    @MockK
    lateinit var queuePreActivity: QueuePreActivityImpl

    private var future: Future<Boolean> = mockk()

    private val employee: Employee = mockk()

    private val coffee: Coffee = mockk()

    private val payment: Payment = mockk()

    private val queueCallable: QueueCallable = mockk()

    @BeforeEach
    @Throws(ExecutionException::class, InterruptedException::class)
    fun setUp() {
        every { future.get() } returns true
    }

    @Test
    @Throws(Exception::class)
    fun startSimulationProcess() {
        val testMachinesFile = javaClass.getResourceAsStream("/coffemachine_example_test_1.xml").shouldNotBeNull()
        val testEmployeesFile = javaClass.getResourceAsStream("/employees_example_test_1.xml").shouldNotBeNull()
        every { machineProcessor.paymentProcessor } returns paymentProcessor
        every { machineProcessor.coffeeProcessor } returns coffeeProcessor
        generalProcessor.initSimulationProcess(
            testMachinesFile,
            testEmployeesFile
        )
        val coffeeMachines: List<CoffeeMachine> = generalProcessor.coffeeMachines.coffeeMachine
        val employees: List<Employee> = generalProcessor.employees.employee
        val expectedDescriptions = Stack<String>()
        expectedDescriptions.push(POURING_COFFEE)
        expectedDescriptions.push(SWITCH_TIME)
        expectedDescriptions.push(POURING_MILK)
        expectedDescriptions.push(GRINDING_COFFEE)
        expectedDescriptions.push(HEATING)
        expectedDescriptions.push(POURING_COFFEE)
        expectedDescriptions.push(SWITCH_TIME)
        expectedDescriptions.push(POURING_MILK)
        expectedDescriptions.push(GRINDING_COFFEE)
        expectedDescriptions.push(HEATING)
        expectedDescriptions.push(POURING_COFFEE)
        expectedDescriptions.push(SWITCH_TIME)
        expectedDescriptions.push(POURING_MILK)
        expectedDescriptions.push(GRINDING_COFFEE)
        expectedDescriptions.push(HEATING)
        expectedDescriptions.push(POURING_COFFEE)
        expectedDescriptions.push(SWITCH_TIME)
        expectedDescriptions.push(POURING_MILK)
        expectedDescriptions.push(GRINDING_COFFEE)
        expectedDescriptions.push(HEATING)
        val expectedCoffeeNames = Stack<String>()
        expectedCoffeeNames.push(LATTE_MACHIATTO_MILD)
        expectedCoffeeNames.push(LATTE_MACHIATTO)
        expectedCoffeeNames.push(LATTE_MACHIATTO_MILD)
        expectedCoffeeNames.push(LATTE_MACHIATTO)
        val expectedCoffeeMachineNames = Stack<String>()
        expectedCoffeeMachineNames.push(NESSY_EXPRESSO_2)
        expectedCoffeeMachineNames.push(NESSY_EXPRESSO_1)
        val expectedTimesForCoffe = Stack<Int>()
        expectedTimesForCoffe.push(40)
        expectedTimesForCoffe.push(30)
        expectedTimesForCoffe.push(20)
        expectedTimesForCoffe.push(10)
        expectedTimesForCoffe.push(5)
        expectedTimesForCoffe.push(40)
        expectedTimesForCoffe.push(30)
        expectedTimesForCoffe.push(20)
        expectedTimesForCoffe.push(10)
        expectedTimesForCoffe.push(5)
        expectedTimesForCoffe.push(40)
        expectedTimesForCoffe.push(30)
        expectedTimesForCoffe.push(20)
        expectedTimesForCoffe.push(10)
        expectedTimesForCoffe.push(5)
        expectedTimesForCoffe.push(40)
        expectedTimesForCoffe.push(30)
        expectedTimesForCoffe.push(20)
        expectedTimesForCoffe.push(10)
        expectedTimesForCoffe.push(5)
        val expectedPaymentTypes = Stack<String>()
        expectedPaymentTypes.push(BEFORE_COFFEE_PAYMENT)
        expectedPaymentTypes.push(AFTER_COFFEE_PAYMENT)
        expectedPaymentTypes.push(WHILE_COFFEE_POURING_PAYMENT)
        expectedPaymentTypes.push(NO_PAYMENT)
        expectedPaymentTypes.push(BEFORE_COFFEE_PAYMENT)
        expectedPaymentTypes.push(AFTER_COFFEE_PAYMENT)
        expectedPaymentTypes.push(WHILE_COFFEE_POURING_PAYMENT)
        expectedPaymentTypes.push(NO_PAYMENT)
        val expectedTimes = Stack<Int?>()
        expectedTimes.push(20)
        expectedTimes.push(10)
        expectedTimes.push(5)
        expectedTimes.push(null)
        expectedTimes.push(20)
        expectedTimes.push(10)
        expectedTimes.push(5)
        expectedTimes.push(null)
        val expectedEmployeeNames = Stack<String>()
        expectedEmployeeNames.push("Marco")
        expectedEmployeeNames.push("Joao")
        val expectedCupSizes = Stack<String>()
        expectedCupSizes.push("Small")
        expectedCupSizes.push("Big")
        val expectedPreActionDescriptions = Stack<String>()
        expectedPreActionDescriptions.push("put cup in outlet")
        expectedPreActionDescriptions.push("choose a cup")
        expectedPreActionDescriptions.push("put cup in outlet")
        expectedPreActionDescriptions.push("choose a cup")
        val expectedPreActionTimes = Stack<Int>()
        expectedPreActionTimes.push(20)
        expectedPreActionTimes.push(10)
        expectedPreActionTimes.push(20)
        expectedPreActionTimes.push(10)
        val expectedPostActionDescriptions = Stack<String>()
        expectedPostActionDescriptions.push("dummy")
        expectedPostActionDescriptions.push("take cup and leave")
        expectedPostActionDescriptions.push("dummy")
        expectedPostActionDescriptions.push("take cup and leave")
        val expectedPostActionTimes = Stack<Int>()
        expectedPostActionTimes.push(55)
        expectedPostActionTimes.push(5)
        expectedPostActionTimes.push(55)
        expectedPostActionTimes.push(5)
        Truth.assertThat(coffeeMachines).hasSize(2)
        Truth.assertThat(employees).hasSize(2)
        coffeeMachines.forEach { coffeeMachine ->
            val coffees = coffeeMachine.coffees.coffee
            val paymentTypes = coffeeMachine.paymentTypes.payment
            Truth.assertThat(coffeeMachine.name).isEqualTo(expectedCoffeeMachineNames.pop())
            Truth.assertThat(coffees).hasSize(2)
            coffees.forEach { coffee ->
                Truth.assertThat(expectedCoffeeNames.pop()).isEqualTo(coffee.name)
                coffee.timesToFill.fillTime.forEach{ fillTime: FillTime ->
                        fillTime.description shouldBe expectedDescriptions.pop()
                        fillTime.value shouldBe expectedTimesForCoffe.pop()
                    }
            }
            paymentTypes.forEach { payment: Payment ->
                Truth.assertThat(payment.name).isEqualTo(expectedPaymentTypes.pop())
                Truth.assertThat(payment.time).isEqualTo(expectedTimes.pop())
            }
        }
        employees.forEach { employee ->
            val preActions = employee.actions.preAction
            val postActions = employee.actions.postAction
            employee.name shouldBe expectedEmployeeNames.pop()
            employee.cup.size shouldBe expectedCupSizes.pop()
            preActions.forEach { action: Employee.Actions.PreAction ->
                action.description shouldBe expectedPreActionDescriptions.pop()
                action.time shouldBe expectedPreActionTimes.pop()
            }
            postActions.forEach { postAction: PostAction ->
               postAction.description shouldBe expectedPostActionDescriptions.pop()
               postAction.time shouldBe expectedPostActionTimes.pop()
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun start() {
        val threadPoolExecutorPreActivity:ThreadPoolExecutor = mockk()
        val threadPoolExecutorPostActivity:ThreadPoolExecutor = mockk()
        val threadPoolExecutorCoffee:ThreadPoolExecutor = mockk()
        val threadPoolExecutorPayment:ThreadPoolExecutor = mockk()
        val testMachinesFile = javaClass.getResourceAsStream("/coffemachine_example_test_1.xml").shouldNotBeNull()
        val testEmployeesFile = javaClass.getResourceAsStream("/employees_example_test_1.xml").shouldNotBeNull()
        every { machineProcessor.paymentProcessor } returns paymentProcessor
        every { machineProcessor.coffeeProcessor } returns coffeeProcessor
        every { machineProcessor.preProcessor } returns preProcessor
        every { machineProcessor.postProcessor } returns postProcessor
        val preActions: List<Employee.Actions.PreAction> = ArrayList()
        val postActions: List<PostAction> = ArrayList()
        generalProcessor.initSimulationProcess(
            testMachinesFile,
            testEmployeesFile
        )
        every { queuePreActivity.getExecutor(any()) } returns threadPoolExecutorPreActivity
        every { queueCoffee.getExecutor(any()) } returns threadPoolExecutorCoffee
        every { queuePayment.getExecutor(any()) } returns threadPoolExecutorPayment
        every { queuePostActivity.getExecutor(any()) } returns threadPoolExecutorPostActivity
        val preActivityExecutorMap = HashMap<String, ThreadPoolExecutor>()
        preActivityExecutorMap[GeneralProcessorImpl.MAIN_QUEUE_PRE] = threadPoolExecutorPreActivity
        preActivityExecutorMap[GeneralProcessorImpl.MAIN_QUEUE_POST] = threadPoolExecutorPostActivity
        val coffeExecutorMap = HashMap<String, ThreadPoolExecutor>()
        coffeExecutorMap[TEST] = threadPoolExecutorCoffee
        val paymentExecutorMap = HashMap<String, ThreadPoolExecutor>()
        paymentExecutorMap[TEST] = threadPoolExecutorPayment
        val postActivityExecutorMap = HashMap<String, ThreadPoolExecutor>()
        postActivityExecutorMap[GeneralProcessorImpl.MAIN_QUEUE_PRE] = threadPoolExecutorPreActivity
        postActivityExecutorMap[GeneralProcessorImpl.MAIN_QUEUE_POST] = threadPoolExecutorPostActivity
        every { threadPoolExecutorPreActivity.submit(any()) } returns future
        every { threadPoolExecutorCoffee.submit(any()) } returns future
        every { threadPoolExecutorPayment.submit(any()) } returns future
        every { threadPoolExecutorPostActivity.submit(any()) } returns future
        every { queuePreActivity.executorServiceMap } returns preActivityExecutorMap
        every { queueCoffee.executorServiceMap } returns coffeExecutorMap
        every { queuePayment.executorServiceMap } returns paymentExecutorMap
        every { queuePostActivity.executorServiceMap } returns postActivityExecutorMap
        every { threadPoolExecutorPreActivity.submit(any<PreActionCallableImpl>()) } returns future
        every { threadPoolExecutorCoffee.submit(any<CoffeeCallableImpl>()) } returns future
        every { threadPoolExecutorPayment.submit(any<PaymentCallableImpl>()) } returns future
        every { threadPoolExecutorPostActivity.submit(any<PostActionCallableImpl>()) } returns future
        generalProcessor.start()
//        Mockito.verify(threadPoolExecutorPreActivity, Mockito.times(2)).submit<Any>(
//            any(
//                Callable::class.java
//            )
//        )
        //        verify(threadPoolExecutorCoffee, times(10)).submit(any(Callable.class));
        //        verify(threadPoolExecutorPayment, times(2)).submit(any(Callable.class));
        //        verify(threadPoolExecutorPostActivity, times(2)).submit(any(Callable.class));
//        verify { preProcessor.callPreActions(employee, GeneralProcessorImpl.MAIN_QUEUE_PRE, preActions, coffee, payment, postActions) }
//        verify { coffeeProcessor.callMakeCoffee(employee, TEST, coffee, payment, postActions, queueCallable) }
//        verify { paymentProcessor.callPayCoffee(employee, TEST, payment, postActions, queueCallable) }
//        verify { postProcessor.callPostActions(GeneralProcessorImpl.MAIN_QUEUE_POST, postActions, queueCallable) }
//        verify { queuePreActivity.initExecutors() }
//        verify { queueCoffee.initExecutors() }
//        verify { queuePayment.initExecutors() }
//        verify { queuePostActivity.initExecutors() }

        //        order.verify(machineProcessor, times(1)).callPreActions(any(Employee.class), eq(MAIN_QUEUE_PRE), any(), any(Coffee.class), any(Payment.class), any());
        //        order.verify(machineProcessor, times(1)).callMakeCoffee(any(Employee.class), any(String.class), any(Coffee.class), any(Payment.class), postActions, any(QueueCallable.class));
        //        order.verify(machineProcessor, times(1)).callPayCoffee(any(Employee.class), any(String.class), any(Payment.class), postActions, any(QueueCallable.class));
        //        order.verify(machineProcessor, times(1)).callPostActions(any(Employee.class), MAIN_QUEUE_POST, postActions, any(QueueCallable.class));
        //        order.verify(machineProcessor, times(1)).callPreActions(any(Employee.class), MAIN_QUEUE_PRE, preActions, any(Coffee.class), any(Payment.class), postActions);
        //        order.verify(machineProcessor, times(1)).callMakeCoffee(any(Employee.class), any(String.class), any(Coffee.class), any(Payment.class), postActions, any(QueueCallable.class));
        //        order.verify(machineProcessor, times(1)).callPayCoffee(any(Employee.class), any(String.class), any(Payment.class), postActions, any(QueueCallable.class));
        //        order.verify(machineProcessor, times(1)).callPostActions(any(Employee.class), MAIN_QUEUE_POST, postActions, any(QueueCallable.class));
    }

    companion object {
        private const val POURING_COFFEE = "pouring coffee"
        private const val SWITCH_TIME = "switch time"
        private const val POURING_MILK = "pouring milk"
        private const val GRINDING_COFFEE = "grinding coffee"
        private const val HEATING = "heating"
        private const val LATTE_MACHIATTO_MILD = "latteMachiattoMild"
        private const val LATTE_MACHIATTO = "latteMachiatto"
        private const val NESSY_EXPRESSO_2 = "nessyExpresso2"
        private const val NESSY_EXPRESSO_1 = "nessyExpresso1"
        private const val BEFORE_COFFEE_PAYMENT = "beforeCoffeePayment"
        private const val AFTER_COFFEE_PAYMENT = "afterCoffeePayment"
        private const val WHILE_COFFEE_POURING_PAYMENT = "whileCoffeePouringPayment"
        private const val NO_PAYMENT = "noPayment"
        private const val TEST = "TEST"
    }
}