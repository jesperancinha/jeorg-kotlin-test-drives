package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.Employees;
import com.steelzack.coffee.system.input.Employees.Employee.Actions;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.sun.javafx.binding.StringFormatter;
import com.sun.org.apache.xerces.internal.util.MessageFormatter;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.Formatter;
import java.util.List;
import java.util.concurrent.Callable;

@Getter
public class EmployeeCallableImpl implements Employee, Callable<Boolean> {
    final Logger logger = Logger.getLogger(EmployeeCallableImpl.class);
    private final Actions actions;
    private Employees.Employee employee;

    public EmployeeCallableImpl(Actions actions, Employees.Employee employee) {
        this.actions = actions;
        this.employee = employee;
    }

    @Override
    public Boolean call() throws Exception {
        logger.info(StringFormatter.format("Employee {0} is waiting in line", employee.getName()));
        final List<Actions.PreAction> preActions = this.actions.getPreAction();
        preActions.stream().forEach( //
                preAction -> { //

                } //
        ); //
        final List<Actions.PostAction> postActions = this.actions.getPostAction();
        postActions.stream().forEach( //
                postAction -> { //

                } //
        ); //
        return true;
    }
}
