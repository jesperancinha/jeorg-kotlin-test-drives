package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine;
import com.steelzack.coffee.system.input.Employees;
import com.steelzack.coffee.system.manager.GeneralProcessor;
import com.steelzack.coffee.system.manager.GeneralProcessorImpl;
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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeCallableImplTest {

    private final InputStream testMachinesFile = getClass().getResourceAsStream("/coffemachine_example_test_1.xml");
    private final InputStream testEmployeesFile = getClass().getResourceAsStream("/employees_example_test_1.xml");

    private final GeneralProcessor generalProcessor = getBuild();

    private GeneralProcessorImpl getBuild() throws FileNotFoundException, JAXBException, SAXException {
        GeneralProcessorImpl build = GeneralProcessorImpl.builder().nIterations(1).build();
        build.initSimulationProcess(
                testMachinesFile, //
                testEmployeesFile
        ); //
        return build;
    }

    private final Employees.Employee employeeChosen = generalProcessor.getEmployees().getEmployee().get(0);
    private final CoffeMachine coffeeMachineChosen = generalProcessor.getCoffeeMachines().getCoffeMachine().get(0);

    @InjectMocks
    private EmployeeCallableImpl employee = getBooleanCallable();

    @Mock
    private ManagedExecutorService managedExecutorService;

    @Test
    public void call() throws Exception {
        when(managedExecutorService.submit(any(Callable.class))) //
                .then((Answer<Future>) invocationOnMock -> new Future<Boolean>() {

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
        });
        ((Callable<Boolean>) employee).call();
    }

    public EmployeeCallableImplTest() throws FileNotFoundException, JAXBException, SAXException {
    }

    private EmployeeCallableImpl getBooleanCallable() throws FileNotFoundException, JAXBException, SAXException {
        return new EmployeeCallableImpl(
              employeeChosen.getActions(),employeeChosen,coffeeMachineChosen.getCoffees().getCoffee().get(0)
                ,coffeeMachineChosen.getPaymentTypes().getPayment().get(0));
    }

}