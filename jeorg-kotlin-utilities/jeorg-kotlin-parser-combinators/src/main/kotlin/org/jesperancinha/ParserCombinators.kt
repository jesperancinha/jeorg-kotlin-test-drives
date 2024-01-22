package org.jesperancinha

fun <T, R> TextParser<T>.then(textParser: TextParser<R>): TextParser<Pair<T, R>> = { input ->
    this(input).let { (resultOfFirstParser, _, remainingOfFirstParser) ->
        remainingOfFirstParser.map {
            textParser(it).let { (resultOfSecondParser, countOfSecondParser, remainingOfSecondParser) ->
                Result(Pair(resultOfFirstParser, resultOfSecondParser), countOfSecondParser, remainingOfSecondParser)
            }
        }
            .takeIf { it.isNotEmpty() }
            ?.reduce { acc, pairResult ->
                Result(
                    acc.expected,
                    acc.count + pairResult.count,
                    acc.remainder + pairResult.remainder
                )
            } ?: Pair(resultOfFirstParser, null as R).emptyResult()
    }
}

infix fun <T, R> TextParser<T>.or(textParser: TextParser<R>): TextParser<Map<T, R>> = { input ->
    this(input).let { (resultOfFirstParser1, count1, remainingOfFirstParser1) ->
        textParser(input).let { (resultOfFirstParser2, count2, remainingOfFirstParser2)
            ->
            Result(
                mapOf(
                    resultOfFirstParser1 to resultOfFirstParser2
                ),
                count1 + count2,
                remainingOfFirstParser1 + remainingOfFirstParser2
            )
        }
    }
}