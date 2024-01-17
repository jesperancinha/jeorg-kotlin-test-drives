package org.jesperancinha.ktd.unique
    data class Book(
        val id: Long
    )
    fun List<Book>.distinct() = this.distinctBy { it.id }
    fun main() {
        val books = listOf(Book(1), Book(2), Book(4), Book(1))
        println(books.distinct())

        GreatClass(1)
        GreatClass("1")
    }


/**
 * Great class to store values
 *
 * @property text A String
 * @constructor Store a string.
 * If you want to use an integer please use the secondary constructor with an [Int] as input argument
 */
class GreatClass(private val text: String) {

    /**
     * This is the other constructor
     * @param aNumber Integer
     */
    constructor(aNumber: Int) : this("You gave me a $aNumber")

}