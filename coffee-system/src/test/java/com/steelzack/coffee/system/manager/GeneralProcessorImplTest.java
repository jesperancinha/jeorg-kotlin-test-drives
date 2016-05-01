package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.CoffeeMachines;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
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

    private static final String POURING_COFFEE = "pouring coffee";
    private static final String SWITCH_TIME = "switch time";
    private static final String POURING_MILK = "pouring milk";
    private static final String GRINDING_COFFEE = "grinding coffee";
    private static final String HEATING = "heating";
    private static final String LATTE_MACHIATTO_MILD = "latteMachiattoMild";
    private static final String LATTE_MACHIATTO = "latteMachiatto";
    private static final String NESSY_EXPRESSO_2 = "nessyExpresso2";
    private static final String NESSY_EXPRESSO_1 = "nessyExpresso1";
    public static final String BEFORE_COFFEE_PAYMENT = "beforeCoffeePayment";
    public static final String AFTER_COFFEE_PAYMENT = "afterCoffeePayment";
    public static final String WHILE_COFFEE_POURING_PAYMENT = "whileCoffeePouringPayment";
    public static final String NO_PAYMENT = "noPayment";

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

        assertThat(coffeMachines.size(), is(2));
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
    }

}