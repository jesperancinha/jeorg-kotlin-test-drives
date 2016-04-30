package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.input.Employees.Employee;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeProcessorImpl implements EmployeeProcessor {
    private final Employee employee ;
}
