package org.jesperancinha

data class Result<T>(
    val expected: T,
    val count: Int,
    val remainder: List<String>
)

typealias TextParser<T> = (String) -> Result<T>

fun <T> T.emptyResult() = Result(this, 0, emptyList())
fun charCountParser(expected: Char): TextParser<Char> = { input ->
    val splits = input.split(expected)
    val size = splits.size - 1
    when (input.isNotEmpty() && size > 0) {
        true -> Result(expected, size, splits.filter { it.isNotEmpty() })
        else -> expected.emptyResult()
    }
}

fun charCountOnStartParser(expected: Char): TextParser<Char> = { input ->
    val splits = input.split(expected)
    val size = splits.size - 1
    when (input.isNotEmpty() && input.startsWith(expected) && size > 0) {
        true -> Result(expected, size, splits.filter { it.isNotEmpty() })
        else -> expected.emptyResult()
    }
}

fun stringCountParser(expected: String): TextParser<String> = { input ->
    val splits = input.split(expected)
    val size = splits.size - 1
    when (input.isNotEmpty() && size > 0) {
        true -> Result(expected, size, splits.filter { it.isNotEmpty() })
        else -> expected.emptyResult()
    }
}

fun stringCountOnStartParser(expected: String): TextParser<String> = { input ->
    val splits = input.split(expected)
    val size = splits.size - 1
    when (input.isNotEmpty() && input.startsWith(expected) && size > 0) {
        true -> Result(expected, size, splits.filter { it.isNotEmpty() })
        else -> expected.emptyResult()
    }
}