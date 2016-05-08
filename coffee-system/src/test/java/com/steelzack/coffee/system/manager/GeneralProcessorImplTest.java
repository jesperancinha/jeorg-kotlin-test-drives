package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.concurrency.CoffeCallable;
import com.steelzack.coffee.system.concurrency.CoffeeCallableImpl;
import com.steelzack.coffee.system.concurrency.PaymentCallable;
import com.steelzack.coffee.system.concurrency.PaymentCallableImpl;
import com.steelzack.coffee.system.concurrency.PostActionCallable;
import com.steelzack.coffee.system.concurrency.PostActionCallableImpl;
import com.steelzack.coffee.system.concurrency.PreActionCallable;
import com.steelzack.coffee.system.concurrency.PreActionCallableImpl;
import com.steelzack.coffee.system.concurrency.QueueCallable;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.steelzack.coffee.system.input.Employees.Employee;
import com.steelzack.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.steelzack.coffee.system.input.Employees.Employee.Actions.PreAction;
import com.steelzack.coffee.system.queues.QueueCofeeImpl;
import com.steelzack.coffee.system.queues.QueuePaymentImpl;
import com.steelzack.coffee.system.queues.QueuePostActivityImpl;
import com.steelzack.coffee.system.queues.QueuePreActivityImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import static com.steelzack.coffee.system.manager.GeneralProcessorImpl.MAIN_QUEUE_POST;
import static com.steelzack.coffee.system.manager.GeneralProcessorImpl.MAIN_QUEUE_PRE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class GeneralProcessorImplTest {

    private static final String POURING_COFFEE = "pouring coffee";
    private static final String SWITCH_TIME = "switch time";
    private static final String POURING_MILK = "pouring milk";
    private static final String GRINDING_COFFEE = "grinding coffee";
    private static final String HEATING = "heating";
    private static final String LATTE_MACHIATTO_MILD = "latteMachiattoMild";
    private static final String LATTE_MACHIATTO = "latteMachiatto";
    private static final String NESSY_EXPRESSO_2 = "nessyExpresso2";
    private static final String NESSY_EXPRESSO_1 = "nessyExpresso1";
    private static final String BEFORE_COFFEE_PAYMENT = "beforeCoffeePayment";
    private static final String AFTER_COFFEE_PAYMENT = "afterCoffeePayment";
    private static final String WHILE_COFFEE_POURING_PAYMENT = "whileCoffeePouringPayment";
    private static final String NO_PAYMENT = "noPayment";
    private static final String TEST = "TEST";

    @InjectMocks
    private GeneralProcessor generalProcessor = GeneralProcessorImpl.builder().nIterations(1).preRowSize(2).postRowSize(2).build();

    @Mock
    private MachineProcessor machineProcessor = new MachineProcessorImpl();

    @InjectMocks
    private PreProcessor preProcessor = new PreProcessorImpl();

    @InjectMocks
    private CoffeeProcessor coffeeProcessor = new CoffeeProcessorImpl();

    @InjectMocks
    private PaymentProcessor paymentProcessor = new PaymentProcessorImpl();

    @InjectMocks
    private PostProcessor postProcessor = new PostProcessorImpl();

    @Mock
    private QueueCofeeImpl queueCofee;

    @Mock
    private QueuePaymentImpl queuePayment;

    @Mock
    private QueuePostActivityImpl queuePostActivity;

    @Mock
    private QueuePreActivityImpl queuePreActivity;

    private Future<Boolean> future;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws ExecutionException, InterruptedException {
        MockitoAnnotations.initMocks(this);
        future = mock(Future.class);
        when(future.get()).thenReturn(true);
    }

    @Test
    public void startSimulationProcess() throws Exception {
        final InputStream testMachinesFile = getClass().getResourceAsStream("/coffemachine_example_test_1.xml");
        final InputStream testEmployeesFile = getClass().getResourceAsStream("/employees_example_test_1.xml");

        when(machineProcessor.getPaymentProcessor()).thenReturn(paymentProcessor);
        when(machineProcessor.getCoffeeProcessor()).thenReturn(coffeeProcessor);

        generalProcessor.initSimulationProcess(
                testMachinesFile, //
                testEmployeesFile //
        ); //

        final List<CoffeMachine> coffeMachines = generalProcessor.getCoffeeMachines().getCoffeMachine();
        final List<Employee> employees = generalProcessor.getEmployees().getEmployee();

        final Stack<String> expectedDescriptions = new Stack<>();
        expectedDescriptions.push(POURING_COFFEE);
        expectedDescriptions.push(SWITCH_TIME);
        expectedDescriptions.push(POURING_MILK);
        expectedDescriptions.push(GRINDING_COFFEE);
        expectedDescriptions.push(HEATING);
        expectedDescriptions.push(POURING_COFFEE);
        expectedDescriptions.push(SWITCH_TIME);
        expectedDescriptions.push(POURING_MILK);
        expectedDescriptions.push(GRINDING_COFFEE);
        expectedDescriptions.push(HEATING);
        expectedDescriptions.push(POURING_COFFEE);
        expectedDescriptions.push(SWITCH_TIME);
        expectedDescriptions.push(POURING_MILK);
        expectedDescriptions.push(GRINDING_COFFEE);
        expectedDescriptions.push(HEATING);
        expectedDescriptions.push(POURING_COFFEE);
        expectedDescriptions.push(SWITCH_TIME);
        expectedDescriptions.push(POURING_MILK);
        expectedDescriptions.push(GRINDING_COFFEE);
        expectedDescriptions.push(HEATING);

        final Stack<String> expectedCoffeeNames = new Stack<>();
        expectedCoffeeNames.push(LATTE_MACHIATTO_MILD);
        expectedCoffeeNames.push(LATTE_MACHIATTO);
        expectedCoffeeNames.push(LATTE_MACHIATTO_MILD);
        expectedCoffeeNames.push(LATTE_MACHIATTO);

        final Stack<String> expectedCoffeeMachineNames = new Stack<>();
        expectedCoffeeMachineNames.push(NESSY_EXPRESSO_2);
        expectedCoffeeMachineNames.push(NESSY_EXPRESSO_1);

        final Stack<Integer> expectedTimesForCoffe = new Stack<>();
        expectedTimesForCoffe.push(40);
        expectedTimesForCoffe.push(30);
        expectedTimesForCoffe.push(20);
        expectedTimesForCoffe.push(10);
        expectedTimesForCoffe.push(5);
        expectedTimesForCoffe.push(40);
        expectedTimesForCoffe.push(30);
        expectedTimesForCoffe.push(20);
        expectedTimesForCoffe.push(10);
        expectedTimesForCoffe.push(5);
        expectedTimesForCoffe.push(40);
        expectedTimesForCoffe.push(30);
        expectedTimesForCoffe.push(20);
        expectedTimesForCoffe.push(10);
        expectedTimesForCoffe.push(5);
        expectedTimesForCoffe.push(40);
        expectedTimesForCoffe.push(30);
        expectedTimesForCoffe.push(20);
        expectedTimesForCoffe.push(10);
        expectedTimesForCoffe.push(5);

        final Stack<String> expectedPaymentTypes = new Stack<>();
        expectedPaymentTypes.push(BEFORE_COFFEE_PAYMENT);
        expectedPaymentTypes.push(AFTER_COFFEE_PAYMENT);
        expectedPaymentTypes.push(WHILE_COFFEE_POURING_PAYMENT);
        expectedPaymentTypes.push(NO_PAYMENT);
        expectedPaymentTypes.push(BEFORE_COFFEE_PAYMENT);
        expectedPaymentTypes.push(AFTER_COFFEE_PAYMENT);
        expectedPaymentTypes.push(WHILE_COFFEE_POURING_PAYMENT);
        expectedPaymentTypes.push(NO_PAYMENT);

        final Stack<Integer> expectedTimes = new Stack<>();
        expectedTimes.push(20);
        expectedTimes.push(10);
        expectedTimes.push(5);
        expectedTimes.push(null);
        expectedTimes.push(20);
        expectedTimes.push(10);
        expectedTimes.push(5);
        expectedTimes.push(null);

        final Stack<String> expectedEmployeeNames = new Stack<>();
        expectedEmployeeNames.push("Marco");
        expectedEmployeeNames.push("Joao");

        final Stack<String> expectedCupSizes = new Stack<>();
        expectedCupSizes.push("Small");
        expectedCupSizes.push("Big");

        final Stack<String> expectedPreActionDescriptions = new Stack<>();
        expectedPreActionDescriptions.push("put cup in outlet");
        expectedPreActionDescriptions.push("choose a cup");
        expectedPreActionDescriptions.push("put cup in outlet");
        expectedPreActionDescriptions.push("choose a cup");

        final Stack<Integer> expectedPreActionTimes = new Stack<>();
        expectedPreActionTimes.push(20);
        expectedPreActionTimes.push(10);
        expectedPreActionTimes.push(20);
        expectedPreActionTimes.push(10);

        final Stack<String> expectedPostActionDescriptions = new Stack<>();
        expectedPostActionDescriptions.push("dummy");
        expectedPostActionDescriptions.push("take cup and leave");
        expectedPostActionDescriptions.push("dummy");
        expectedPostActionDescriptions.push("take cup and leave");

        final Stack<Integer> expectedPostActionTimes = new Stack<>();
        expectedPostActionTimes.push(55);
        expectedPostActionTimes.push(5);
        expectedPostActionTimes.push(55);
        expectedPostActionTimes.push(5);

        assertThat(coffeMachines.size(), is(2));
        assertThat(employees.size(), is(2));
        coffeMachines.stream().forEach( //
                coffeeMachine -> { //
                    final List<Coffee> coffees = coffeeMachine.getCoffees().getCoffee(); //
                    final List<Payment> paymentTypes = coffeeMachine.getPaymentTypes().getPayment();
                    assertThat(coffeeMachine.getName(), equalTo(expectedCoffeeMachineNames.pop())); //
                    assertThat(coffees.size(), is(2)); //
                    coffees.stream().forEach( //
                            coffee -> { //
                                assertThat(expectedCoffeeNames.pop(), equalTo(coffee.getName())); //
                                coffee.getTimesToFill().getFillTime().stream().forEach( //
                                        fillTime -> { //
                                            assertThat(fillTime.getDescription(), equalTo(expectedDescriptions.pop())); //
                                            assertThat(fillTime.getValue(), equalTo(expectedTimesForCoffe.pop()));
                                        }
                                );
                            }
                    );
                    paymentTypes.stream().forEach(
                            payment -> {
                                assertThat(payment.getName(), equalTo(expectedPaymentTypes.pop()));
                                assertThat(payment.getTime(), equalTo(expectedTimes.pop()));
                            }
                    );
                }
        );
        employees.stream().forEach(
                employee -> {
                    final List<PreAction> preActions = employee.getActions().getPreAction();
                    final List<PostAction> postActions = employee.getActions().getPostAction();
                    assertThat(employee.getName(), equalTo(expectedEmployeeNames.pop()));
                    assertThat(employee.getCup().getSize(), equalTo(expectedCupSizes.pop()));
                    preActions.stream().forEach(
                            action -> {
                                assertThat(action.getDescription(), equalTo(expectedPreActionDescriptions.pop()));
                                assertThat(action.getTime(), equalTo(expectedPreActionTimes.pop()));
                            }
                    );
                    postActions.stream().forEach(
                            postAction -> {
                                assertThat(postAction.getDescription(), equalTo(expectedPostActionDescriptions.pop()));
                                assertThat(postAction.getTime(), equalTo(expectedPostActionTimes.pop()));
                            }
                    );
                }
        );
    }

    @Test
    public void start() throws Exception {
        final ThreadPoolExecutor threadPoolExecutorPreActivity = mock(ThreadPoolExecutor.class);
        final ThreadPoolExecutor threadPoolExecutorPostActivity = mock(ThreadPoolExecutor.class);
        final ThreadPoolExecutor threadPoolExecutorCoffee = mock(ThreadPoolExecutor.class);
        final ThreadPoolExecutor threadPoolExecutorPayment = mock(ThreadPoolExecutor.class);

        final InputStream testMachinesFile = getClass().getResourceAsStream("/coffemachine_example_test_1.xml");
        final InputStream testEmployeesFile = getClass().getResourceAsStream("/employees_example_test_1.xml");

        when(machineProcessor.getPaymentProcessor()).thenReturn(paymentProcessor);
        when(machineProcessor.getCoffeeProcessor()).thenReturn(coffeeProcessor);
        when(machineProcessor.getPreProcessor()).thenReturn(preProcessor);
        when(machineProcessor.getPostProcessor()).thenReturn(postProcessor);

        final List<PreAction> preActions = new ArrayList<>();
        final List<PostAction> postActions = new ArrayList<>();

        generalProcessor.initSimulationProcess(
                testMachinesFile, //
                testEmployeesFile //
        ); //

        doAnswer(invocationOnMock -> { //
            preProcessor.callPreActions(null, MAIN_QUEUE_PRE, preActions, null, null, null); //
            return null; //
        }).when(machineProcessor).callPreActions(any(Employee.class), eq(MAIN_QUEUE_PRE), any(), any(Coffee.class), any(Payment.class), any());
        doAnswer(invocationOnMock -> { //
            coffeeProcessor.callMakeCoffee(null, TEST, null, null, null, null); //
            return null; //
        }).when(machineProcessor).callMakeCoffee(any(Employee.class), any(String.class), any(Coffee.class), any(Payment.class), any(), any(QueueCallable.class));
        doAnswer(invocationOnMock -> { //
            paymentProcessor.callPayCoffee(null, TEST, null, null, null); //
            return null; //
        }).when(machineProcessor).callPayCoffee(any(Employee.class), any(String.class), any(Payment.class), any(), any(QueueCallable.class));
        doAnswer(invocationOnMock -> { //
            postProcessor.callPostActions(any(Employee.class), MAIN_QUEUE_POST, postActions, null); //
            return null; //
        }).when(machineProcessor).callPostActions(any(Employee.class), eq(MAIN_QUEUE_POST), any(), any(QueueCallable.class));

        when(queuePreActivity.getExecutor(any(String.class))).thenReturn(threadPoolExecutorPreActivity);
        when(queueCofee.getExecutor(any(String.class))).thenReturn(threadPoolExecutorCoffee);
        when(queuePayment.getExecutor(any(String.class))).thenReturn(threadPoolExecutorPayment);
        when(queuePostActivity.getExecutor(any(String.class))).thenReturn(threadPoolExecutorPostActivity);

        final HashMap<String, ThreadPoolExecutor> preActivityExecutorMap = new HashMap<>();
        preActivityExecutorMap.put(MAIN_QUEUE_PRE, threadPoolExecutorPreActivity);
        preActivityExecutorMap.put(MAIN_QUEUE_POST, threadPoolExecutorPostActivity);
        final HashMap<String, ThreadPoolExecutor> coffeExecutorMap = new HashMap<>();
        coffeExecutorMap.put(TEST, threadPoolExecutorCoffee);
        final HashMap<String, ThreadPoolExecutor> paymentExecutorMap = new HashMap<>();
        paymentExecutorMap.put(TEST, threadPoolExecutorPayment);
        final HashMap<String, ThreadPoolExecutor> postActivityExecutorMap = new HashMap<>();
        postActivityExecutorMap.put(MAIN_QUEUE_PRE, threadPoolExecutorPreActivity);
        postActivityExecutorMap.put(MAIN_QUEUE_POST, threadPoolExecutorPostActivity);

        when(threadPoolExecutorPreActivity.submit(any(PreActionCallable.class))).thenReturn(future);
        when(threadPoolExecutorCoffee.submit(any(CoffeCallable.class))).thenReturn(future);
        when(threadPoolExecutorPayment.submit(any(PaymentCallable.class))).thenReturn(future);
        when(threadPoolExecutorPostActivity.submit(any(PostActionCallable.class))).thenReturn(future);

        when(queuePreActivity.getExecutorServiceMap()).thenReturn(preActivityExecutorMap);
        when(queueCofee.getExecutorServiceMap()).thenReturn(coffeExecutorMap);
        when(queuePayment.getExecutorServiceMap()).thenReturn(paymentExecutorMap);
        when(queuePostActivity.getExecutorServiceMap()).thenReturn(postActivityExecutorMap);

        when(threadPoolExecutorPreActivity.submit(any(PreActionCallableImpl.class))).thenReturn(future);
        when(threadPoolExecutorCoffee.submit(any(CoffeeCallableImpl.class))).thenReturn(future);
        when(threadPoolExecutorPayment.submit(any(PaymentCallableImpl.class))).thenReturn(future);
        when(threadPoolExecutorPostActivity.submit(any(PostActionCallableImpl.class))).thenReturn(future);

        final InOrder order = inOrder(machineProcessor);

        generalProcessor.start();

        verify(threadPoolExecutorPreActivity, times(2)).submit(any(Callable.class));
//        verify(threadPoolExecutorCoffee, times(10)).submit(any(Callable.class));
//        verify(threadPoolExecutorPayment, times(2)).submit(any(Callable.class));
//        verify(threadPoolExecutorPostActivity, times(2)).submit(any(Callable.class));

        verify(queuePreActivity, times(1)).initExecutors();
        verify(queueCofee, times(1)).initExecutors();
        verify(queuePayment, times(1)).initExecutors();
        verify(queuePostActivity, times(1)).initExecutors();

//        order.verify(machineProcessor, times(1)).callPreActions(any(Employee.class), eq(MAIN_QUEUE_PRE), any(), any(Coffee.class), any(Payment.class), any());
//        order.verify(machineProcessor, times(1)).callMakeCoffee(any(Employee.class), any(String.class), any(Coffee.class), any(Payment.class), postActions, any(QueueCallable.class));
//        order.verify(machineProcessor, times(1)).callPayCoffee(any(Employee.class), any(String.class), any(Payment.class), postActions, any(QueueCallable.class));
//        order.verify(machineProcessor, times(1)).callPostActions(any(Employee.class), MAIN_QUEUE_POST, postActions, any(QueueCallable.class));
//        order.verify(machineProcessor, times(1)).callPreActions(any(Employee.class), MAIN_QUEUE_PRE, preActions, any(Coffee.class), any(Payment.class), postActions);
//        order.verify(machineProcessor, times(1)).callMakeCoffee(any(Employee.class), any(String.class), any(Coffee.class), any(Payment.class), postActions, any(QueueCallable.class));
//        order.verify(machineProcessor, times(1)).callPayCoffee(any(Employee.class), any(String.class), any(Payment.class), postActions, any(QueueCallable.class));
//        order.verify(machineProcessor, times(1)).callPostActions(any(Employee.class), MAIN_QUEUE_POST, postActions, any(QueueCallable.class));
    }

}
