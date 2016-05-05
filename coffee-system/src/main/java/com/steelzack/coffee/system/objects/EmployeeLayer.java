package com.steelzack.coffee.system.objects;

import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import com.steelzack.coffee.system.input.Employees;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
@Builder
@Getter
public class EmployeeLayer {
    private Employees.Employee employee;

    private Coffee coffee;

    private Payment payment;
}
