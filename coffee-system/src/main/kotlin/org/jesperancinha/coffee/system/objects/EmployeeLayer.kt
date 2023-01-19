package org.jesperancinha.coffee.system.objects

import lombok.Builder
import lombok.Getter
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
@Builder
@Getter
class EmployeeLayer {
    private val employee: Employee? = null
    private val coffee: Coffee? = null
    private val payment: Payment? = null
}