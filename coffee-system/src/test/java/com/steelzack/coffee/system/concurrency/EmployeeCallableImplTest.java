package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine;
import com.steelzack.coffee.system.input.Employees;
import com.steelzack.coffee.system.manager.GeneralProcessor;
import com.steelzack.coffee.system.manager.GeneralProcessorImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.xml.sax.SAXException;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeCallableImplTest {

    private static Future<Boolean> future = new Future<Boolean>() {

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return false;
        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public boolean isDone() {
            return true;
        }

        @Override
        public Boolean get() throws InterruptedException, ExecutionException {
            return true;
        }

        @Override
        public Boolean get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return true;
        }

    };

    private static GeneralProcessor generalProcessor;

    private static Employees.Employee employeeChosen;

    private static CoffeMachine coffeeMachineChosen;

    @BeforeClass
    public static void setUp() throws FileNotFoundException, JAXBException, SAXException {
        final InputStream testMachinesFile = EmployeeCallableImplTest.class.getResourceAsStream("/coffemachine_example_test_1.xml");
        final InputStream testEmployeesFile = EmployeeCallableImplTest.class.getResourceAsStream("/employees_example_test_1.xml");
        generalProcessor = getBuild(testMachinesFile, testEmployeesFile);
        employeeChosen = generalProcessor.getEmployees().getEmployee().get(0);
        coffeeMachineChosen = generalProcessor.getCoffeeMachines().getCoffeMachine().get(0);
    }

    @InjectMocks
    private EmployeeCallableImpl employee = getBooleanCallable();

    @Mock
    private ManagedExecutorService managedExecutorService;

    @Test
    public void call() throws Exception {
        when(managedExecutorService.submit(any(Callable.class))) //
                .then((Answer<Future>) invocationOnMock -> {
                    return future;
                });
        ((Callable<Boolean>) employee).call();

        verify(managedExecutorService, times(2)).submit(isA(PreActionCallableImpl.class));
        verify(managedExecutorService, times(2)).submit(isA(PostActionCallableImpl.class));
        verify(managedExecutorService, times(5)).submit(isA(CoffeeCallableImpl.class));
        verify(managedExecutorService, times(1)).submit(isA(PaymentCallableImpl.class));

        InOrder inOrder = inOrder(managedExecutorService);
        inOrder.verify(managedExecutorService, times(2)).submit(isA(PreActionCallableImpl.class));
        inOrder.verify(managedExecutorService, times(5)).submit(isA(CoffeeCallableImpl.class));
        inOrder.verify(managedExecutorService, times(1)).submit(isA(PaymentCallableImpl.class));
        inOrder.verify(managedExecutorService, times(2)).submit(isA(PostActionCallableImpl.class));
    }

    public EmployeeCallableImplTest() throws FileNotFoundException, JAXBException, SAXException {
    }

    private EmployeeCallableImpl getBooleanCallable() throws FileNotFoundException, JAXBException, SAXException {
        final CoffeMachine.Coffees.Coffee chosenCoffee = coffeeMachineChosen.getCoffees().getCoffee().get(0);
        final CoffeMachine.PaymentTypes.Payment chosenPayment = coffeeMachineChosen.getPaymentTypes().getPayment().get(0);
        return new EmployeeCallableImpl( //
                //
                employeeChosen, //
                chosenCoffee, //
                chosenPayment //
        );
    }

    private static GeneralProcessorImpl getBuild(InputStream testMachinesFile, InputStream testEmployeesFile) throws FileNotFoundException, JAXBException, SAXException {
        GeneralProcessorImpl build = GeneralProcessorImpl.builder().nIterations(1).build();
        build.initSimulationProcess(
                testMachinesFile, //
                testEmployeesFile
        ); //
        return build;
    }

}