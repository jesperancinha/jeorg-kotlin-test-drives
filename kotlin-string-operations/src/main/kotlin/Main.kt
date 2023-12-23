package org.jesperancinha

fun main() {
    val testString= "---I want to start at the beginning without indentation"
    val resultString = testString
        .replaceIndentByMargin("", "---")
    println(
        resultString
    )
}