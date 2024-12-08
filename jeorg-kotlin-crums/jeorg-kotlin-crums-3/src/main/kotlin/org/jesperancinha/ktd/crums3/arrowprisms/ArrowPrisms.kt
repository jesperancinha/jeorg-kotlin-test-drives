package org.jesperancinha.ktd.crums3.arrowprisms

import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import arrow.optics.Prism
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.ktd.crums3.arrowprisms.ComputationResult.Success

private const val FIRST_MESSAGE = "Welcome the free access files!"
private const val SECOND_MESSAGE = "Welcome to the secret files!"
private const val COMPUTATION_ITERATIONS = "2"

sealed class ComputationResult {
    data class Success(val content: String) : ComputationResult()
    object Failure : ComputationResult()
}
val networkResult: Success = Success(FIRST_MESSAGE)

val networkResultInt: Success = Success(COMPUTATION_ITERATIONS)

/**
 * The Arrow Project has had its ups and downs and massive changes are constantly happening
 * If you are not finding a particular code that you expected to find here, it is important to mention a few changes
 *
 * From Arrow 2.0.0 onwards, these extension functions do not exist anymore:
 *
 * import arrow.core.andThen
 * import arrow.core.compose
 *
 * This compromises some example code that used to be here, especially code related to functors, monoids and monads
 */
class ArrowPrisms {
    companion object {
        private val logger = object {
            fun info(logText: Any) = ConsolerizerComposer.out().red(logText)
            fun infoComment(logText: Any) = ConsolerizerComposer.out().lightGrey(logText)

            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .yellow(ConsolerizerComposer.title(logText))
        }

        @JvmStatic
        fun main(args: Array<String>) {
            logger.infoTitle("Crum 6 - Arrow Prisms Example")

            val stringBasedPrism: Prism<ComputationResult, Success> = Prism(
                getOrModify = { networkResult ->
                    when (networkResult) {
                        is Success -> networkResult.right()
                        else -> networkResult.left()
                    }
                },
                reverseGet = { networkResult -> networkResult }
            )
            val intBasedGenericPrism: Prism<ComputationResult, Success> = Prism(
                getOrModify = { networkResult ->
                    when (networkResult) {
                        is Success -> networkResult.right()
                        else -> networkResult.left()
                    }
                },
                reverseGet = { networkResult -> networkResult }
            )
            val intBasedPrism: Prism<Success, Int> = Prism(
                getOption = { success ->
                    success.content.toIntOrNull().toOption()
                },
                reverseGet = { number -> Success("Success: $number") }
            )
            val composedPrism: Prism<ComputationResult, Int> = stringBasedPrism compose intBasedPrism


            val modify: ComputationResult = stringBasedPrism.modify(networkResult) { success ->
                success.copy(content = SECOND_MESSAGE)
            }
            val modifyIntWrong: ComputationResult = composedPrism.modify(networkResult) { integrity ->
                integrity * 1000
            }
            val modifyIntRight: ComputationResult = composedPrism.modify(networkResultInt) { integrity ->
                integrity * 1000
            }
            val lifted: (ComputationResult) -> ComputationResult = stringBasedPrism.lift { success ->
                success.copy(content = SECOND_MESSAGE)
            }

            val result: ComputationResult = lifted(ComputationResult.Failure)
            val result2: ComputationResult = lifted(Success("2"))
            val result3: ComputationResult = lifted(Success(COMPUTATION_ITERATIONS))
            val result4: ComputationResult = lifted(Success(SECOND_MESSAGE))


            logger.infoComment("All Results")
            logger.info(networkResult.content)
            logger.info(networkResultInt.content)
            logger.infoComment("Accepts all")
            logger.info(stringBasedPrism.getOrNull(networkResult)?.content ?: "No Focus Found")
            logger.info(stringBasedPrism.getOrNull(networkResultInt)?.content ?: "No Focus Found")
            logger.infoComment("Just Integers")
            logger.info(intBasedPrism.getOrNull(networkResult) ?: "No Focus Found")
            logger.info(intBasedPrism.getOrNull(networkResultInt) ?: "No Focus Found")
            logger.infoComment("Composed")
            logger.info(composedPrism.getOrNull(networkResult) ?: "No Focus Found")
            logger.info(composedPrism.getOrNull(networkResultInt) ?: "No Focus Found")
            logger.infoComment("Modify and ModifyInt")
            logger.info(modify)
            logger.info(modifyIntWrong)
            logger.infoComment("Left Right Modify")
            logger.info(modifyIntWrong.left().getOrNull() ?: "Nothing found!")
            logger.info(modifyIntWrong.right().getOrNull() ?: "Nothing found!")
            logger.infoComment("Uses Modify")
            logger.info(stringBasedPrism.getOrNull(modify)?.content ?: "No Focus Found")
            logger.info(stringBasedPrism.getOrNull(modifyIntWrong)?.content ?: "No Focus Found")
            logger.info(intBasedGenericPrism.getOrNull(modifyIntWrong)?.content ?: "No Focus Found")
            logger.info(intBasedGenericPrism.getOrNull(modifyIntRight)?.content ?: "No Focus Found")
            logger.infoComment("Lifted Usage")
            logger.info(lifted)
            logger.info(result)
            logger.info(result2)
            logger.info(result3)
            logger.info(composedPrism.getOrNull(result) ?: "No Focus Found")
            logger.info(composedPrism.getOrNull(result2) ?: "No Focus Found")
            logger.info(composedPrism.getOrNull(result3) ?: "No Focus Found")
            logger.info(composedPrism.getOrNull(result4) ?: "No Focus Found")
            logger.info(stringBasedPrism.getOrNull(result)?.content ?: "No Focus Found")
            logger.info(stringBasedPrism.getOrNull(result2)?.content ?: "No Focus Found")
            logger.info(stringBasedPrism.getOrNull(result3)?.content ?: "No Focus Found")
            logger.info(stringBasedPrism.getOrNull(result4)?.content ?: "No Focus Found")
            logger.info(intBasedPrism.getOrNull(Success("Welcome to the wrong files")) ?: "No Focus Found")
            logger.info(intBasedPrism.getOrNull(Success("2")) ?: "No Focus Found")
            logger.info(intBasedPrism.getOrNull(Success("Nothing works")) ?: "No Focus Found")
            logger.info(intBasedPrism.getOrNull(Success("1000000")) ?: "No Focus Found")
        }
    }
}