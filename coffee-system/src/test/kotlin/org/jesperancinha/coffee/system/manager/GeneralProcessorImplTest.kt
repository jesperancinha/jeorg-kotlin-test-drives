package org.jesperancinha.coffee.system.manager

import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.concurrency.CoffeeCallableImpl
import org.jesperancinha.coffee.system.concurrency.PaymentCallableImpl
import org.jesperancinha.coffee.system.concurrency.PostActionCallableImpl
import org.jesperancinha.coffee.system.concurrency.PreActionCallableImpl
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee.TimesToFill.FillTime
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.queues.QueueCofeeImpl
import org.jesperancinha.coffee.system.queues.QueuePaymentImpl
import org.jesperancinha.coffee.system.queues.QueuePostActivityImpl
import org.jesperancinha.coffee.system.queues.QueuePreActivityImpl
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import java.util.*
import java.util.concurrent.ExecutionException
import java.util.concurrent.Future
import java.util.concurrent.ThreadPoolExecutor
import java.util.function.Consumer

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
@ExtendWith(MockKExtension::class)
class GeneralProcessorImplTest {
    @InjectMockKs
    lateinit var generalProcessor: GeneralProcessorImpl

    @MockK
    lateinit var machineProcessor: MachineProcessorImpl

    @MockK
    lateinit var preProcessor: PreProcessorImpl

    @MockK
    lateinit var coffeeProcessor: CoffeeProcessorImpl

    @MockK
    lateinit var paymentProcessor: PaymentProcessorImpl

    @MockK
    lateinit var postProcessor: PostProcessorImpl

    @MockK
    lateinit var queueCofee: QueueCofeeImpl

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

    @Before
    @Throws(ExecutionException::class, InterruptedException::class)
    fun setUp() {
        every { future.get() } returns true
    }

    @Test
    @Throws(Exception::class)
    fun startSimulationProcess() {
        val testMachinesFile = javaClass.getResourceAsStream("/coffemachine_example_test_1.xml")
        val testEmployeesFile = javaClass.getResourceAsStream("/employees_example_test_1.xml")
        Mockito.`when`(machineProcessor.paymentProcessor).thenReturn(paymentProcessor)
        Mockito.`when`(machineProcessor.coffeeProcessor).thenReturn(coffeeProcessor)
        generalProcessor.initSimulationProcess(
            testMachinesFile,
            testEmployeesFile
        )
        val coffeMachines: List<CoffeMachine?> = generalProcessor.coffeeMachines.coffeMachine
        val employees: List<Employee?> = generalProcessor.employees.getEmployee()
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
        Truth.assertThat(coffeMachines).hasSize(2)
        Truth.assertThat(employees).hasSize(2)
        coffeMachines.forEach(
            Consumer { coffeeMachine: CoffeMachine? ->
                val coffees = coffeeMachine.coffees.coffee
                val paymentTypes = coffeeMachine.paymentTypes.payment
                Truth.assertThat(coffeeMachine.name).isEqualTo(expectedCoffeeMachineNames.pop())
                Truth.assertThat(coffees).hasSize(2)
                coffees.stream().forEach { coffee: Coffee? ->
                    Truth.assertThat(expectedCoffeeNames.pop()).isEqualTo(coffee.name)
                    coffee.timesToFill.fillTime.forEach(
                        Consumer { fillTime: FillTime ->
                            Truth.assertThat(fillTime.description)
                                .isEqualTo(expectedDescriptions.pop()) //
                            Truth.assertThat(fillTime.value).isEqualTo(expectedTimesForCoffe.pop())
                        }
                    )
                }
                paymentTypes.forEach(
                    Consumer { payment: Payment ->
                        Truth.assertThat(payment.name).isEqualTo(expectedPaymentTypes.pop())
                        Truth.assertThat(payment.time).isEqualTo(expectedTimes.pop())
                    }
                )
            }
        )
        employees.forEach(
            Consumer { employee: Employee? ->
                val preActions = employee.actions.preAction
                val postActions = employee.actions.postAction
                Truth.assertThat(employee.name).isEqualTo(expectedEmployeeNames.pop())
                Truth.assertThat(employee.cup.size).isEqualTo(expectedCupSizes.pop())
                preActions.forEach(
                    Consumer { action: Employee.Actions.PreAction ->
                        Truth.assertThat(action.description)
                            .isEqualTo(expectedPreActionDescriptions.pop())
                        Truth.assertThat(action.time).isEqualTo(expectedPreActionTimes.pop())
                    }
                )
                postActions.forEach(
                    Consumer { postAction: PostAction ->
                        Truth.assertThat(postAction.description).isEqualTo(expectedPostActionDescriptions.pop())
                        Truth.assertThat(postAction.time).isEqualTo(expectedPostActionTimes.pop())
                    }
                )
            }
        )
    }

    @Test
    @Throws(Exception::class)
    fun start() {
        val threadPoolExecutorPreActivity = Mockito.mock(
            ThreadPoolExecutor::class.java
        )
        val threadPoolExecutorPostActivity = Mockito.mock(
            ThreadPoolExecutor::class.java
        )
        val threadPoolExecutorCoffee = Mockito.mock(
            ThreadPoolExecutor::class.java
        )
        val threadPoolExecutorPayment = Mockito.mock(
            ThreadPoolExecutor::class.java
        )
        val testMachinesFile = javaClass.getResourceAsStream("/coffemachine_example_test_1.xml")
        val testEmployeesFile = javaClass.getResourceAsStream("/employees_example_test_1.xml")
        Mockito.`when`(machineProcessor.paymentProcessor).thenReturn(paymentProcessor)
        Mockito.`when`(machineProcessor.coffeeProcessor).thenReturn(coffeeProcessor)
        Mockito.`when`(machineProcessor.preProcessor).thenReturn(preProcessor)
        Mockito.`when`(machineProcessor.postProcessor).thenReturn(postProcessor)
        val preActions: List<Employee.Actions.PreAction> = ArrayList()
        val postActions: List<PostAction> = ArrayList()
        generalProcessor.initSimulationProcess(
            testMachinesFile,
            testEmployeesFile
        )
        Mockito.doAnswer {
            preProcessor.callPreActions(
                employee,
                GeneralProcessorImpl.MAIN_QUEUE_PRE,
                preActions,
                coffee,
                payment,
                postActions
            )
            null
        }.`when`(machineProcessor)
            .callPreActions(
                Matchers.any(Employee::class.java),
                Matchers.eq(GeneralProcessorImpl.MAIN_QUEUE_PRE),
                Matchers.any(),
                Matchers.any(
                    Coffee::class.java
                ),
                Matchers.any(Payment::class.java),
                Matchers.any()
            )
        Mockito.doAnswer {
            coffeeProcessor.callMakeCoffee(employee, TEST, coffee, payment, postActions, queueCallable)
            null
        }.`when`(machineProcessor)
            .callMakeCoffee(
                Matchers.any(Employee::class.java), Matchers.any(
                    String::class.java
                ), Matchers.any(Coffee::class.java), Matchers.any(
                    Payment::class.java
                ), Matchers.any(),
                Matchers.any(QueueCallable::class.java)
            )
        Mockito.doAnswer {
            paymentProcessor.callPayCoffee(employee, TEST, payment, postActions, queueCallable)
            null
        }.`when`(machineProcessor).callPayCoffee(
            Matchers.any(Employee::class.java), Matchers.any(
                String::class.java
            ), Matchers.any(Payment::class.java), Matchers.any(),
            Matchers.any(QueueCallable::class.java)
        )
        Mockito.doAnswer {
            postProcessor.callPostActions(
                Matchers.any(
                    Employee::class.java
                ), GeneralProcessorImpl.MAIN_QUEUE_POST, postActions, queueCallable
            )
            null
        }.`when`(machineProcessor)
            .callPostActions(
                Matchers.any(Employee::class.java),
                Matchers.eq(GeneralProcessorImpl.MAIN_QUEUE_POST),
                Matchers.any(),
                Matchers.any(
                    QueueCallable::class.java
                )
            )
        Mockito.`when`(
            queuePreActivity.getExecutor(
                Matchers.any(
                    String::class.java
                )
            )
        ).thenReturn(threadPoolExecutorPreActivity)
        Mockito.`when`(
            queueCofee.getExecutor(
                Matchers.any(
                    String::class.java
                )
            )
        ).thenReturn(threadPoolExecutorCoffee)
        Mockito.`when`(
            queuePayment.getExecutor(
                Matchers.any(
                    String::class.java
                )
            )
        ).thenReturn(threadPoolExecutorPayment)
        Mockito.`when`(
            queuePostActivity.getExecutor(
                Matchers.any(
                    String::class.java
                )
            )
        ).thenReturn(threadPoolExecutorPostActivity)
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
        Mockito.`when`(
            threadPoolExecutorPreActivity.submit(
                Matchers.any(
                    PreActionCallableImpl::class.java
                )
            )
        ).thenReturn(future)
        Mockito.`when`(
            threadPoolExecutorCoffee.submit(
                Matchers.any(
                    CoffeeCallableImpl::class.java
                )
            )
        ).thenReturn(future)
        Mockito.`when`(
            threadPoolExecutorPayment.submit(
                Matchers.any(
                    PaymentCallableImpl::class.java
                )
            )
        ).thenReturn(future)
        Mockito.`when`(
            threadPoolExecutorPostActivity.submit(
                Matchers.any(
                    PostActionCallableImpl::class.java
                )
            )
        ).thenReturn(future)
        Mockito.`when`<Map<String, ThreadPoolExecutor>>(queuePreActivity.executorServiceMap)
            .thenReturn(preActivityExecutorMap)
        Mockito.`when`<Map<String, ThreadPoolExecutor>>(queueCofee.executorServiceMap).thenReturn(coffeExecutorMap)
        Mockito.`when`<Map<String, ThreadPoolExecutor>>(queuePayment.executorServiceMap).thenReturn(paymentExecutorMap)
        Mockito.`when`<Map<String, ThreadPoolExecutor>>(queuePostActivity.executorServiceMap)
            .thenReturn(postActivityExecutorMap)
        Mockito.`when`(
            threadPoolExecutorPreActivity.submit(
                Matchers.any(
                    PreActionCallableImpl::class.java
                )
            )
        ).thenReturn(future)
        Mockito.`when`(
            threadPoolExecutorCoffee.submit(
                Matchers.any(
                    CoffeeCallableImpl::class.java
                )
            )
        ).thenReturn(future)
        Mockito.`when`(
            threadPoolExecutorPayment.submit(
                Matchers.any(
                    PaymentCallableImpl::class.java
                )
            )
        ).thenReturn(future)
        Mockito.`when`(
            threadPoolExecutorPostActivity.submit(
                Matchers.any(
                    PostActionCallableImpl::class.java
                )
            )
        ).thenReturn(future)
        val order = Mockito.inOrder(machineProcessor)
        generalProcessor.start()
//        Mockito.verify(threadPoolExecutorPreActivity, Mockito.times(2)).submit<Any>(
//            Matchers.any(
//                Callable::class.java
//            )
//        )
        //        verify(threadPoolExecutorCoffee, times(10)).submit(any(Callable.class));
        //        verify(threadPoolExecutorPayment, times(2)).submit(any(Callable.class));
        //        verify(threadPoolExecutorPostActivity, times(2)).submit(any(Callable.class));
        Mockito.verify(queuePreActivity, Mockito.times(1)).initExecutors()
        Mockito.verify(queueCofee, Mockito.times(1)).initExecutors()
        Mockito.verify(queuePayment, Mockito.times(1)).initExecutors()
        Mockito.verify(queuePostActivity, Mockito.times(1)).initExecutors()

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