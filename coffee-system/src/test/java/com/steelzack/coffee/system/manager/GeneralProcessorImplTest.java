package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.CoffeeMachines;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
public class GeneralProcessorImplTest {

    @Test
    public void startSimulationProcess() throws Exception {
        final InputStream testMachinesFile = getClass().getResourceAsStream("/coffemachine_example_test_1.xml");
        final InputStream testEmployeesFile = getClass().getResourceAsStream("/employees_example_test_1.xml");
        final GeneralProcessor generalProcessor = GeneralProcessorImpl.builder().nIterations(1).build();

        generalProcessor.startSimulationProcess(
                testMachinesFile, //
                testEmployeesFile
        ); //

        final List<CoffeeMachines.CoffeMachine> coffeMachines = generalProcessor.getCoffeeMachines().getCoffeMachine();

        final Stack<String> expectedDescriptions = new Stack<>();
        expectedDescriptions.push("pouring coffee");
        expectedDescriptions.push("switch time");
        expectedDescriptions.push("pouring milk");
        expectedDescriptions.push("grinding coffee");
        expectedDescriptions.push("heating");
        expectedDescriptions.push("pouring coffee");
        expectedDescriptions.push("switch time");
        expectedDescriptions.push("pouring milk");
        expectedDescriptions.push("grinding coffee");
        expectedDescriptions.push("heating");
        expectedDescriptions.push("pouring coffee");
        expectedDescriptions.push("switch time");
        expectedDescriptions.push("pouring milk");
        expectedDescriptions.push("grinding coffee");
        expectedDescriptions.push("heating");
        expectedDescriptions.push("pouring coffee");
        expectedDescriptions.push("switch time");
        expectedDescriptions.push("pouring milk");
        expectedDescriptions.push("grinding coffee");
        expectedDescriptions.push("heating");

        final Stack<String> expectedCoffeeNames = new Stack<>();
        expectedCoffeeNames.push("latteMachiattoMild");
        expectedCoffeeNames.push("latteMachiatto");
        expectedCoffeeNames.push("latteMachiattoMild");
        expectedCoffeeNames.push("latteMachiatto");

        final Stack<String> expectedCoffeeMachineNames = new Stack<>();
        expectedCoffeeMachineNames.push("nessyExpresso2");
        expectedCoffeeMachineNames.push("nessyExpresso1");

        assertThat(coffeMachines.size(), is(2));
        coffeMachines.stream().forEach( //
                coffeeMachine -> { //
                    List<CoffeeMachines.CoffeMachine.Coffees.Coffee> coffees = coffeeMachine.getCoffees().getCoffee(); //
                    assertThat(coffeeMachine.getName(), equalTo(expectedCoffeeMachineNames.pop())); //
                    assertThat(coffees.size(), is(2)); //
                    coffees.stream().forEach( //
                            coffee -> { //
                                assertThat(expectedCoffeeNames.pop(), equalTo(coffee.getName())); //
                                coffee.getTimesToFill().getFillTime().stream().forEach( //
                                        fillTime -> { //
                                            assertThat(fillTime.getDescription(), equalTo(expectedDescriptions.pop())); //
                                        }
                                );
                            }
                    );
                }
        );
    }

}