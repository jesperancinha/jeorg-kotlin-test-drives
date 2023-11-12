package org.jesperancinha.ktd

import java.time.LocalDateTime

interface Person {
    val name: String
    val address: String
}

interface Card {
    val cardNumber: Long
    val expiryDate: LocalDateTime
}

data class Client(
    override val name: String,
    override val address: String
) : Person

data class DebitCard(
    override val cardNumber: Long,
    override val expiryDate: LocalDateTime
) : Card

class Account(
    person: Person,
    card: Card,
    val number: String
) : Person by person, Card by card


class User(details: Map<String, String>) {
    val name by details
    val address by details

    override fun toString(): String = "User name is $name and address is $address"
}

class Delegates {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            val account = Account(
                Client(
                    name = "João",
                    address = "World"
                ),
                DebitCard(
                    cardNumber = 1111111111111111,
                    expiryDate = LocalDateTime.now()
                ),
                number = "AAABBB12312313-3243242-23432"
            )
            println(account.number)
            println(account.name)
            println(account.address)
            println(account.cardNumber)
            println(account.expiryDate)

            val user = User(mapOf("name" to "Priscilla", "address" to "Silicon Valley"))
            println(user)
        }
    }
}