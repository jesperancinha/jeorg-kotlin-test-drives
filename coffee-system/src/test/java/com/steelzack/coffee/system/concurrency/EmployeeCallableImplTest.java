package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine;
import com.steelzack.coffee.system.input.Employees;
import com.steelzack.coffee.system.manager.GeneralProcessor;
import com.steelzack.coffee.system.manager.GeneralProcessorImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeCallableImplTest {

    private static Future future;

    private static GeneralProcessor generalProcessor;

    private static Employees.Employee employeeChosen;

    private static CoffeMachine coffeeMachineChosen;

    @BeforeClass
    public static void setUp() throws FileNotFoundException, JAXBException, SAXException, ExecutionException, InterruptedException {
        final InputStream testMachinesFile = EmployeeCallableImplTest.class.getResourceAsStream("/coffemachine_example_test_1.xml");
        final InputStream testEmployeesFile = EmployeeCallableImplTest.class.getResourceAsStream("/employees_example_test_1.xml");
        generalProcessor = getBuild(testMachinesFile, testEmployeesFile);
        employeeChosen = generalProcessor.getEmployees().getEmployee().get(0);
        coffeeMachineChosen = generalProcessor.getCoffeeMachines().getCoffeMachine().get(0);
        future = mock(Future.class);
        when(future.get()).thenReturn(true);
    }

    @InjectMocks
    private EmployeeCallableImpl employee = getBooleanCallable();

    @Mock
    private ManagedExecutorService managedExecutorService;

    @Test
    public void call() throws Exception {
        when(managedExecutorService.submit(any(Callable.class))) //
                .then((Answer<Future>) invocationOnMock -> future);
        employee.call();
    }

    public EmployeeCallableImplTest() throws FileNotFoundException, JAXBException, SAXException {
    }

    private EmployeeCallableImpl getBooleanCallable() throws FileNotFoundException, JAXBException, SAXException {
        final CoffeMachine.Coffees.Coffee chosenCoffee = coffeeMachineChosen.getCoffees().getCoffee().get(0);
        final CoffeMachine.PaymentTypes.Payment chosenPayment = coffeeMachineChosen.getPaymentTypes().getPayment().get(0);
        return new EmployeeCallableImpl( //
                employeeChosen //
        );
    }

    private static GeneralProcessorImpl getBuild(InputStream testMachinesFile, InputStream testEmployeesFile) throws FileNotFoundException, JAXBException, SAXException {
        GeneralProcessorImpl build = GeneralProcessorImpl.builder().nIterations(1).build();
        build.initSimulationProcess(
                testMachinesFile, //
                testEmployeesFile //
        ); //
        return build;
    }

}