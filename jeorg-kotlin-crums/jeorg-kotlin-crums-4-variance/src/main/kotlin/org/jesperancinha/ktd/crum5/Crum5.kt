package org.jesperancinha.ktd.crum5

import org.jesperancinha.ktd.Spoon

abstract class Stick

class  SpoonStick: Stick() {
    override fun toString(): String = "spoon stick"
}

class ForkStick: Stick() {
    override fun toString(): String = "fork stick"
}

class InCup<in T : Stick>(
// Only works with UnsafeVariance
// val stick: @UnsafeVariance T
) {
    fun stirCoffee (stick: T) = println("Coffee stirred with $stick");

}


class Patisserie {
    companion object {
        val inCup1 = InCup<Stick>()
        val inCup2 = InCup<SpoonStick>()

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            inCup1.stirCoffee(object: Stick(){
                override fun toString(): String = "standard stick"
            })
            inCup1.stirCoffee(SpoonStick())
            inCup1.stirCoffee(ForkStick())

            // This not possible
            // inCup2.stirCoffee(ForkStick())

            // However covariant types allow us to do this, but this is not the purpose of it
            (inCup2 as InCup<Stick>).stirCoffee(ForkStick())

            // And if we actually want to now input only a Fork this work with covariant types
            val inCup3: InCup<ForkStick> = inCup2
            // but this is only because that the compiler made an upper-cast of inCup2, making it now of type of InCup<ForkStick>

            // Because of this, this will not work:
            // val inCup4: InCup<SpoonStick> = inCup3

            // but this will
            (inCup3 as InCup<SpoonStick>).stirCoffee(SpoonStick())
            val inCup4: InCup<SpoonStick> = inCup3

            // This is how we typically see contravariance
            // It makes sense and mostly we don't even think about it
            // However this is purely what contravariance means in coding.
            val inCup5: InCup<SpoonStick> = InCup<Stick>()

            // This will never work.
            // Using a ForkStick in this case is not permitted, which means that a reference to Stick doesn't make sense
            // This is the reason why the compiler fails in this case
            // val inCup6: InCup<Stick> = InCup<SpoonStick>()
        }
    }
}