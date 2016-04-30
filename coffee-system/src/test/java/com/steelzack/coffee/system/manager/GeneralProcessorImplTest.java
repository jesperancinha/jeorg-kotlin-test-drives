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

    public static final String POURING_COFFEE = "pouring coffee";
    public static final String SWITCH_TIME = "switch time";
    public static final String POURING_MILK = "pouring milk";
    public static final String GRINDING_COFFEE = "grinding coffee";
    public static final String HEATING = "heating";
    public static final String LATTE_MACHIATTO_MILD = "latteMachiattoMild";
    public static final String LATTE_MACHIATTO = "latteMachiatto";
    public static final String NESSY_EXPRESSO_2 = "nessyExpresso2";
    public static final String NESSY_EXPRESSO_1 = "nessyExpresso1";

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