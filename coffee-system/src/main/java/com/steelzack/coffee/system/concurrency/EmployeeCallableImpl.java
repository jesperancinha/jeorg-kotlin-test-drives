package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.CoffeeMachines;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.steelzack.coffee.system.input.Employees;
import com.steelzack.coffee.system.input.Employees.Employee.Actions;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.sun.javafx.binding.StringFormatter;
import com.sun.org.apache.xerces.internal.util.MessageFormatter;
import lombok.Builder;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.Formatter;
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



        callPostActions();
        return true;
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
