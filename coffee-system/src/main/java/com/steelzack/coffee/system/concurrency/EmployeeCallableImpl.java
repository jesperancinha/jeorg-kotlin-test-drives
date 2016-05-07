package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.Employees;
import com.steelzack.coffee.system.manager.MachineProcessor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Accessors(chain = true)
@Getter
@Service
public class EmployeeCallableImpl implements EmployeeCallable {
    private static final Logger logger = Logger.getLogger(EmployeeCallableImpl.class);
    public static final String SCHEDULED_TASK_FAILED_TO_EXECUTE = "scheduled task faild to execute!";

    @Autowired
    private MachineProcessor machineProcessor;

    private Employees.Employee employee;

    public EmployeeCallableImpl( //
                                 Employees.Employee employee //
    ) {
        this.employee = employee;
    }

    @Override
    public Boolean call() throws Exception {
        logger.info(MessageFormat.format("EmployeeCallable {0} is waiting in line", employee.getName()));
        return true;
    }

}
