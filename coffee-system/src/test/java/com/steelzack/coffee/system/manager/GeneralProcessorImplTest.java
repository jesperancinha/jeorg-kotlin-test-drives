package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.concurrency.CoffeeCallableImpl;
import com.steelzack.coffee.system.concurrency.PaymentCallableImpl;
import com.steelzack.coffee.system.concurrency.PostActionCallableImpl;
import com.steelzack.coffee.system.concurrency.PreActionCallableImpl;
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
import org.mockito.runners.MockitoJUnitRunner;

import java.io.InputStream;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static com.steelzack.coffee.system.manager.GeneralProcessorImpl.MAIN_QUEUE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atMost;
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
    public static final String TEST = "TEST";

    @InjectMocks
    private GeneralProcessor generalProcessor = GeneralProcessorImpl.builder().nIterations(1).preRowSize(2).postRowSize(2).build();

    @Mock
    private MachineProcessor machineProcessor = new MachineProcessorImpl();

    @InjectMocks
    private CoffeeProcessor coffeeProcessor = new CoffeeProcessorImpl();

    @InjectMocks
    private EmployeeProcessor employeeProcessor = new EmployeeProcessorImpl();

    @InjectMocks
    private PaymentProcessor paymentProcessor = new PaymentProcessorImpl();

    @Mock
    private QueueCofeeImpl queueCofee;

    @Mock
    private QueuePaymentImpl queuePayment;

    @Mock
    private QueuePostActivityImpl queuePostActivity;

    @Mock
    private QueuePreActivityImpl queuePreActivity;

    private Future future;

    @Before
    public void setUp() throws ExecutionException, InterruptedException {
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

        final Stack<Byte> expectedTimesForCoffe = new Stack<>();
        expectedTimesForCoffe.push((byte) 40);
        expectedTimesForCoffe.push((byte) 30);
        expectedTimesForCoffe.push((byte) 20);
        expectedTimesForCoffe.push((byte) 10);
        expectedTimesForCoffe.push((byte) 5);
        expectedTimesForCoffe.push((byte) 40);
        expectedTimesForCoffe.push((byte) 30);
        expectedTimesForCoffe.push((byte) 20);
        expectedTimesForCoffe.push((byte) 10);
        expectedTimesForCoffe.push((byte) 5);
        expectedTimesForCoffe.push((byte) 40);
        expectedTimesForCoffe.push((byte) 30);
        expectedTimesForCoffe.push((byte) 20);
        expectedTimesForCoffe.push((byte) 10);
        expectedTimesForCoffe.push((byte) 5);
        expectedTimesForCoffe.push((byte) 40);
        expectedTimesForCoffe.push((byte) 30);
        expectedTimesForCoffe.push((byte) 20);
        expectedTimesForCoffe.push((byte) 10);
        expectedTimesForCoffe.push((byte) 5);

        final Stack<String> expectedPaymentTypes = new Stack<>();
        expectedPaymentTypes.push(BEFORE_COFFEE_PAYMENT);
        expectedPaymentTypes.push(AFTER_COFFEE_PAYMENT);
        expectedPaymentTypes.push(WHILE_COFFEE_POURING_PAYMENT);
        expectedPaymentTypes.push(NO_PAYMENT);
        expectedPaymentTypes.push(BEFORE_COFFEE_PAYMENT);
        expectedPaymentTypes.push(AFTER_COFFEE_PAYMENT);
        expectedPaymentTypes.push(WHILE_COFFEE_POURING_PAYMENT);
        expectedPaymentTypes.push(NO_PAYMENT);

        final Stack<Byte> expectedTimes = new Stack<>();
        expectedTimes.push((byte) 20);
        expectedTimes.push((byte) 10);
        expectedTimes.push((byte) 5);
        expectedTimes.push(null);
        expectedTimes.push((byte) 20);
        expectedTimes.push((byte) 10);
        expectedTimes.push((byte) 5);
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

        final Stack<Byte> expectedPreActionTimes = new Stack<>();
        expectedPreActionTimes.push((byte) 20);
        expectedPreActionTimes.push((byte) 10);
        expectedPreActionTimes.push((byte) 20);
        expectedPreActionTimes.push((byte) 10);

        final Stack<String> expectedPostActionDescriptions = new Stack<>();
        expectedPostActionDescriptions.push("dummy");
        expectedPostActionDescriptions.push("take cup and leave");
        expectedPostActionDescriptions.push("dummy");
        expectedPostActionDescriptions.push("take cup and leave");

        final Stack<Byte> expectedPostActionTimes = new Stack<>();
        expectedPostActionTimes.push((byte) 55);
        expectedPostActionTimes.push((byte) 5);
        expectedPostActionTimes.push((byte) 55);
        expectedPostActionTimes.push((byte) 5);

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
        final ExecutorService managerExecutorServicePreActivity = mock(ExecutorService.class);
        final ExecutorService managerExecutorServicePostActivity = mock(ExecutorService.class);
        final ExecutorService managerExecutorServiceCoffee = mock(ExecutorService.class);
        final ExecutorService managerExecutorServicePayment = mock(ExecutorService.class);

        final InputStream testMachinesFile = getClass().getResourceAsStream("/coffemachine_example_test_1.xml");
        final InputStream testEmployeesFile = getClass().getResourceAsStream("/employees_example_test_1.xml");

        when(machineProcessor.getPaymentProcessor()).thenReturn(paymentProcessor);
        when(machineProcessor.getCoffeeProcessor()).thenReturn(coffeeProcessor);
        when(machineProcessor.getEmployeeProcessor()).thenReturn(employeeProcessor);

        generalProcessor.initSimulationProcess(
                testMachinesFile, //
                testEmployeesFile //
        ); //

        doAnswer(invocationOnMock -> { //
            employeeProcessor.callPreActions(MAIN_QUEUE); //
            return null; //
        }).when(machineProcessor).callPreActions(MAIN_QUEUE);
        doAnswer(invocationOnMock -> { //
            coffeeProcessor.callMakeCoffee(TEST); //
            return null; //
        }).when(machineProcessor).callMakeCoffee(any(String.class));
        doAnswer(invocationOnMock -> { //
            paymentProcessor.callPayCoffee(TEST); //
            return null; //
        }).when(machineProcessor).callPayCoffee(any(String.class));
        doAnswer(invocationOnMock -> { //
            employeeProcessor.callPostActions(MAIN_QUEUE); //
            return null; //
        }).when(machineProcessor).callPostActions(MAIN_QUEUE);


        when(queuePreActivity.getExecutor(any(String.class))).thenReturn(managerExecutorServicePreActivity);
        when(queueCofee.getExecutor(any(String.class))).thenReturn(managerExecutorServiceCoffee);
        when(queuePayment.getExecutor(any(String.class))).thenReturn(managerExecutorServicePayment);
        when(queuePostActivity.getExecutor(any(String.class))).thenReturn(managerExecutorServicePostActivity);

        when(managerExecutorServicePreActivity.submit(any(PreActionCallableImpl.class))).thenReturn(future);
        when(managerExecutorServiceCoffee.submit(any(CoffeeCallableImpl.class))).thenReturn(future);
        when(managerExecutorServicePayment.submit(any(PaymentCallableImpl.class))).thenReturn(future);
        when(managerExecutorServicePostActivity.submit(any(PostActionCallableImpl.class))).thenReturn(future);

        InOrder order = inOrder(machineProcessor);

        generalProcessor.start();

        verify(managerExecutorServicePreActivity, times(4)).submit(any(Callable.class));
        verify(managerExecutorServiceCoffee, times(10)).submit(any(Callable.class));
        verify(managerExecutorServicePayment, times(2)).submit(any(Callable.class));
        verify(managerExecutorServicePostActivity, times(4)).submit(any(Callable.class));

        verify(queuePreActivity, times(2)).getExecutor(any(String.class));
        verify(queueCofee, atMost(10)).getExecutor(any(String.class));
        verify(queuePayment, atMost(2)).getExecutor(any(String.class));
        verify(queuePostActivity, times(2)).getExecutor(any(String.class));

        order.verify(machineProcessor, times(1)).callPreActions(MAIN_QUEUE);
        order.verify(machineProcessor, times(1)).callMakeCoffee(any(String.class));
        order.verify(machineProcessor, times(1)).callPayCoffee(any(String.class));
        order.verify(machineProcessor, times(1)).callPostActions(MAIN_QUEUE);
        order.verify(machineProcessor, times(1)).callPreActions(MAIN_QUEUE);
        order.verify(machineProcessor, times(1)).callMakeCoffee(any(String.class));
        order.verify(machineProcessor, times(1)).callPayCoffee(any(String.class));
        order.verify(machineProcessor, times(1)).callPostActions(MAIN_QUEUE);
    }

}