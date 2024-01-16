package org.jesperancinha.ktd.unique
    data class Book(
        val id: Long
    )
    fun List<Book>.distinct() = this.distinctBy { it.id }
    fun main() {
        val books = listOf(Book(1), Book(2), Book(4), Book(1))
        println(books.distinct())
    }