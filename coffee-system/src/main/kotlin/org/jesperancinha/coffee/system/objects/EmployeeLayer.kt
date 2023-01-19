package org.jesperancinha.coffee.system.objects

import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.Coffees.Coffee
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee

/**
 * Created by joaofilipesabinoesperancinha on 05-05-16.
 */
data class EmployeeLayer(
    val employee: Employee,
    private val coffee: Coffee? = null,
    private val payment: Payment? = null,
)