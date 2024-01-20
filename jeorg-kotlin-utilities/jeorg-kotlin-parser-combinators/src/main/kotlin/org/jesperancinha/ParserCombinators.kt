package org.jesperancinha

fun <T, R> TextParser<T>.then(textParser: TextParser<R>): TextParser<Pair<T, R>> = { input ->
    this(input).let { (resultOfFirstParser, _, remainingOfFirstParser) ->
        remainingOfFirstParser.map {
            textParser(it).let { (resultOfSecondParser, countOfSecondParser, remainingOfSecondParser) ->
                Result(Pair(resultOfFirstParser, resultOfSecondParser), countOfSecondParser, remainingOfSecondParser)
            }
        }
            .takeIf { it.isNotEmpty() }
            ?.reduce { acc, triple ->
                Result(
                    acc.expected,
                    acc.count + triple.count,
                    acc.remainder + triple.remainder
                )
            } ?: Pair(resultOfFirstParser, null as R).emptyResult()
    }
}
