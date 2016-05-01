package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.CoffeeMachines;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee.TimesToFill.FillTime;
import com.steelzack.coffee.system.input.Employees;
import com.steelzack.coffee.system.input.Employees.Employee.Actions;
import com.sun.javafx.binding.StringFormatter;
import lombok.Builder;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.Callable;

@Builder
@Getter
public class EmployeeCallableImpl implements Employee, Callable<Boolean> {
    final Logger logger = Logger.getLogger(EmployeeCallableImpl.class);
    private final Actions actions;
    private final Employees.Employee employee;
    private final Coffee chosenCoffee;
    private final CoffeeMachines.CoffeMachine.PaymentTypes.Payment chosenPayment;

    @Override
    public Boolean call() throws Exception {
        logger.info(StringFormatter.format("Employee {0} is waiting in line", employee.getName()));
        callPreActions();
        callMakeCoffee();
        callPayCoffee();
        callPostActions();
        return true;
    }

    private void callPayCoffee() {

    }

    private void callMakeCoffee() {
        final List<FillTime> tasks = chosenCoffee.getTimesToFill().getFillTime();
        tasks.stream().sorted( //
                (fillTime1, fillTime2)-> fillTime1.getIndex().compareTo(fillTime2.getIndex())
        ).forEach( //
                fillTime -> { //

                }
        );
    }

    private void callPostActions() {
        final List<Actions.PostAction> postActions = this.actions.getPostAction();
        postActions.stream().forEach( //
                postAction -> { //

                } //
        ); //
    }

    private void callPreActions() {
        final List<Actions.PreAction> preActions = this.actions.getPreAction();
        preActions.stream().forEach( //
                preAction -> { //

                } //
        ); //
    }
}
