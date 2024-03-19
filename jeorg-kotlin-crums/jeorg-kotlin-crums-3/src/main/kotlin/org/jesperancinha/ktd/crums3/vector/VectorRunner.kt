package org.jesperancinha.ktd.crums3.vector

import java.util.Vector

/**
 * Note that We shouldn't use vectors in real implementations.
 * Vectors are proven not be efficient in real life scenarios
 * However they are still used in job interviews.
 * This is an example of that
 */
class VectorRunner {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val aVector = Vector<String>()
            aVector.add("Rolling perls of the cliff with moving motions Band")
            println(aVector)
        }
    }
}