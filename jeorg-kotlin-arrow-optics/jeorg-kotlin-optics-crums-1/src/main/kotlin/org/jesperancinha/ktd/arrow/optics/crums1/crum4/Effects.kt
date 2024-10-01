package org.jesperancinha.aktd.crums1.crum2

import arrow.core.raise.effect
import arrow.core.left
import arrow.core.leftIor
import arrow.core.raise.mapError
import arrow.core.raise.toEither
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

data class PostCode(
    val numbers: Int,
    val letters: String
)

fun createPostCode(code: String) = effect {
    try {
        code.take(4).toInt()
        PostCode(1234,"AA")
    } catch (e: Exception) {
        raise(NumberError(e.message))
    }

}

class NumberError(val errorMessage: String?) : Any()

class Effects {
    companion object {
        private val logger = object {
            fun info(logText: Any) = ConsolerizerComposer.out().red(logText)
            fun info2(logText: Any) = ConsolerizerComposer.out().green(logText)
            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .yellow(ConsolerizerComposer.title(logText))

            fun error(logText: Any?) = ConsolerizerComposer.out().brightRed(logText)
        }

        @JvmStatic
        suspend fun main(args: Array<String> = emptyArray()) {
            logger.infoTitle("Crum 4 - Effects simplified")

            logger.info2("Create valid postcode")
            val postCodeCreationResult = createPostCode("1234AA")
            logger.info(postCodeCreationResult)
            logger.info(postCodeCreationResult.toEither())
            logger.info2("Create invalid postcode")
            val postCodeCreationResult2 = createPostCode("BBBBAA")
                .mapError {
                    logger.error(it.errorMessage).let { PostCode(1234,"AA") }
                }
            logger.info(postCodeCreationResult2)
            logger.info(postCodeCreationResult2.toEither())
            logger.info(postCodeCreationResult2.left())
            logger.info(postCodeCreationResult2.leftIor())
        }
    }
}