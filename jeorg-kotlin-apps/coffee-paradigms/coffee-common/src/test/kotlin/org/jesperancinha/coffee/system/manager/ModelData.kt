package org.jesperancinha.coffee.system.manager

import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.Coffees.Coffee
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.Coffees.Coffee.TimesToFill
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.Coffees.Coffee.TimesToFill.FillTime
import org.jesperancinha.coffee.system.input.CoffeeMachines.CoffeeMachine.PaymentTypes.Payment
import org.jesperancinha.coffee.system.input.Employees.Employee
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PostAction
import org.jesperancinha.coffee.system.input.Employees.Employee.Actions.PreAction

val coffee = Coffee().apply {
    name = "Love"
    timesToFill = TimesToFill().apply {
        fillTime.add(FillTime().apply {
            value = 10
            description = "Filling with concerns about the magazine"
            index = 0
        })
    }
}
val employee = Employee().apply {
    name = "Flamboyant Competent and Correct"
    cup = Employee.Cup().apply {
        size = "The Biggest"
        value = "So much better than frustration"
    }
    actions = Employee.Actions().apply {
        preAction.add(PreAction().apply {
            value = "Super good action"
            description = "I talk about music with my colleagues when I'm not working"
            time = 1000
        })
        postAction.add(PostAction().apply {
            value = "A very good feeling"
            description = "We understand each other when we take the time and effort to get to know each other"
            time = 1000000
        })
    }
}
val preActions = employee.actions.preAction
val postActions: MutableList<PostAction> = employee.actions.postAction
val payment = Payment().apply {
    name = "Reformation Cash"
    value = "Cheese"
    time = 0
}