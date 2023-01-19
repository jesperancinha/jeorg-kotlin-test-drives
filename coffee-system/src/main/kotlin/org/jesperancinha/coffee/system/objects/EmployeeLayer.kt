package org.jesperancinha.coffee.system.objects

import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.Coffees.Coffee
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
data class EmployeeLayer(
    val employee: Employee,
    val coffee: Coffee,
    val payment: Payment,
)