package org.jesperancinha.coffee.system.manager

import org.jesperancinha.coffee.system.api.concurrency.QueueCallable
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.Coffees.Coffee
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MachineProcessor(
    @Autowired
    val preProcessor: PreProcessorImpl,
    @Autowired
    val coffeeProcessor: CoffeeProcessorImpl,
    @Autowired
    val paymentProcessor: PaymentProcessorImpl,
    @Autowired
    val postProcessor: PostProcessorImpl
) {

    fun callPreActions(
        employee: Employee,
        name: String,
        preActions: List<Employee.Actions.PreAction>,
        coffee: Coffee,
        payment: Payment,
        postActions: List<PostAction>
    ) {
        preProcessor.callPreActions(employee, name, preActions, coffee, payment, postActions)
    }

    fun callMakeCoffee(
        employee: Employee,
        name: String,
        coffee: Coffee,
        payment: Payment,
        postActions: List<PostAction>,
        parentCallable: QueueCallable
    ) {
        coffeeProcessor.callMakeCoffee(employee, name, coffee, payment, postActions, parentCallable)
    }

    fun callPayCoffee(
        employee: Employee,
        name: String,
        payment: Payment,
        postActions: List<PostAction>,
        parentCallable: QueueCallable
    ) {
        paymentProcessor.callPayCoffee(employee, name, payment, postActions, parentCallable)
    }

    fun callPostActions(
        name: String,
        postActions: List<PostAction>,
        parentCallable: QueueCallable
    ) {
        postProcessor.callPostActions(name, postActions, parentCallable)
    }

    fun initAll() {
        preProcessor.initExecutors()
        coffeeProcessor.initExecutors()
        paymentProcessor.initExecutors()
        postProcessor.initExecutors()
    }
}