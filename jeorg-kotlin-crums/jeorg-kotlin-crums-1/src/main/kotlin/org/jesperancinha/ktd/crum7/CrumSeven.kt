package org.jesperancinha.ktd.crum7

import org.jesperancinha.ktd.eq

/**
 * Created by jofisaes on 14/06/2021
 */
class CrumSeven {
    companion object {
        data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

        fun evaluateGuess(secret: String, guess: String): Evaluation {

            val rightPositions = secret.zip(guess).count { it.first == it.second }

            val commonLetters = "ABCDEF".sumBy { ch ->

                Math.min(secret.count { ch == it }, guess.count { ch == it})
            }
            return Evaluation(rightPositions, commonLetters - rightPositions)
        }

        @JvmStatic
        fun main() {
            val result = Evaluation(rightPosition = 1, wrongPosition = 1)
            evaluateGuess("BCDF", "ACEB") eq result
            evaluateGuess("AAAF", "ABCA") eq result
            evaluateGuess("ABCA", "AAAF") eq result
        }
    }
}