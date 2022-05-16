package org.jesperancinha.ktd.json1

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.ktd.json1.model.Coin
import org.jesperancinha.ktd.json1.model.Currency
import org.jesperancinha.ktd.json1.model.Stamp
import java.time.LocalDate


const val CONSONANTS = "bcdfghjklmnpqrstvwxyz"
const val VOWELS = "aeiou"

/**
 * The main process which will trigger the generation of 100 coins and a 100 stamps by default.
 * It will be configurable in the future
 */
fun main(args: Array<String>) {

    val listCoins = createListOfCoins(100)
    val listStamps = createListOfStamps(100)

    ConsolerizerComposer.outSpace()
        .blue()
        .blue(listCoins.toString())
        .blue(listStamps.toString())
        .reset()
}

fun createListOfCoins(n: Int): List<Coin> {
    return (1..n).map {
        Coin(
            id = it.toLong(),
            description = getRandomNameWithChars(200),
            year = getRandomYearFrom(1900),
            value = getRandomValueUpTo(20),
            currency = getRandomCurrency(),
            diameterMM = getRandomDiameterMM(10, 20),
            internalDiameterMM = getRandomDiameterMM(0, 5)
        )
    }
}

fun createListOfStamps(n: Int): List<Stamp> {
    return (1..n).map {
        Stamp(
            id = it.toLong(),
            description = getRandomNameWithChars(200),
            year = getRandomYearFrom(1900),
            value = getRandomValueUpTo(20),
            currency = getRandomCurrency(),
            heightMM = getRandomDimMM(5, 10),
            widthMM = getRandomDimMM(5, 5)
        )
    }
}

fun getRandomDimMM(i: Int, i1: Int): Long {
    return (Math.random() * (i1 - i).toDouble() + i).toLong()
}

fun getRandomDiameterMM(i: Int, i1: Int): Long {
    return (Math.random() * (i1 - i).toDouble() + i).toLong()
}

fun getRandomCurrency(): Currency {
    return Currency.values()[(Math.random() * Currency.values().size).toInt()]
}

fun getRandomValueUpTo(i: Int): Long {
    return (Math.random() * (i - 1).toDouble() + 1).toLong()
}

fun getRandomYearFrom(i: Int): Long {
    return (Math.random() * (LocalDate.now().year - i) + 1900).toLong()
}

fun getRandomNameWithChars(i: Int): String {
    var composedString = ""
    while (composedString.length < i) {
        composedString += getConsonants(2, 3)
        composedString += randomSpace()
        composedString += getVowels(10, 15)
        composedString += randomSpace()
        if (composedString.length > i) {
            composedString = composedString.substring(0, i)
        }
    }
    return composedString
}

private fun randomSpace(): String {
    if ((Math.random() * 10).toInt() % 2 == 0) {
        return " "
    }
    return ""
}

private fun getConsonants(i: Int, i1: Int): String {
    return (i..i1)
        .map { CONSONANTS[(CONSONANTS.length * Math.random()).toInt()] }
        .joinToString("")
}

private fun getVowels(i: Int, i1: Int): String {
    return (i..i1)
        .map { VOWELS[(VOWELS.length * Math.random()).toInt()] }
        .joinToString("")
}
