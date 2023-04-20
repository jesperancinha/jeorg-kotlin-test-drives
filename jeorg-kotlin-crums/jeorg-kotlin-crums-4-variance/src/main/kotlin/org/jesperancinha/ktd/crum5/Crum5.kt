package org.jesperancinha.ktd.crum5

abstract class Stick

class SpoonStick : Stick() {
    override fun toString(): String = "spoon stick"
}

class ForkStick : Stick() {
    override fun toString(): String = "fork stick"
}

class InvariantCup<T : Stick>(
    private val stick: T,
) {
    fun stirCoffee() = println("Coffee stirred with $stick");

    fun stirCoffee(stick: T) = println("Coffee stirred with $stick");
}

class ContravariantCup<in T : Stick>(
// Only works with UnsafeVariance
// val stick: @UnsafeVariance T
) {
    fun stirCoffee(stick: T) = println("Coffee stirred with $stick");

}

class CovariantCup<out T : Stick>(
    private val stick: T,
    // Var is not possible. An out type should not be changed
    // Otherwise it has to be an in type.
    // var stick:T
) {
    fun stirCoffee() = println("Coffee stirred with $stick").run { stick }

}

class Patisserie {
    companion object {

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            runInvariantExample()

//            runCotravariantExample()

//            runCovariantExample()
        }

        private fun runCovariantExample() {
            println("--- runCovariantExample ---")
            val forTheVideoCoffeeStick: CovariantCup<Stick> = CovariantCup(SpoonStick())
            forTheVideoCoffeeStick.stirCoffee()
            val covariantCup1 = CovariantCup<Stick>(object : Stick() {})
            covariantCup1.stirCoffee()

            // A class covariant to a type is a class where its subtypes are the ones
            // using the subtypes of that type
            // val covariantCup2: CovariantCup<SpoonStick> = covariantCup1
            val covariantCup2: CovariantCup<Stick> = covariantCup1
            covariantCup2.stirCoffee()
            val covariantCup3 = CovariantCup(SpoonStick())
            val covariantCup4: CovariantCup<Stick> = covariantCup3
            covariantCup4.stirCoffee()
        }

        private fun runInvariantExample() {
            println("--- runInvariantExample ---")
//            val forTheVideoCoffeeStick: InvariantCup<Stick> = InvariantCup<SpoonStick>(SpoonStick())
            val invariantCupSpoonStick = InvariantCup(SpoonStick())
            invariantCupSpoonStick.stirCoffee()
            invariantCupSpoonStick.stirCoffee(SpoonStick())
            // This is not possible with classes invariant their types
            // invariantCupSpoonStick.stirCoffee(ForkStick())
            val invariantCupForkStick = InvariantCup(ForkStick())
            invariantCupForkStick.stirCoffee()
            invariantCupForkStick.stirCoffee(ForkStick())
            // This is not possible with classes invariant their types
            // invariantCupForkStick.stirCoffee(SpoonStick())
            val invariantCupStick = InvariantCup<Stick>(object : Stick() {
            })

            // This is not possible because of the anonymous type
            // Although it is a subtype of Stick, the actual type is anonymous
            // val invariantCupStick = InvariantCup(object : Stick() {
            // })
            invariantCupStick.stirCoffee(SpoonStick())
            invariantCupStick.stirCoffee(ForkStick())
        }

        private fun runCotravariantExample() {
            val contravariantCup1 = ContravariantCup<Stick>()
            val contravariantCup2 = ContravariantCup<SpoonStick>()
            println("--- runCotravariantExample ---")
            contravariantCup1.stirCoffee(object : Stick() {
                override fun toString(): String = "standard stick"
            })
            contravariantCup1.stirCoffee(SpoonStick())
            contravariantCup1.stirCoffee(ForkStick())

            // This not possible
            // inCup2.stirCoffee(ForkStick())

            // However covariant types allow us to do this, but this is not the purpose of it
            (contravariantCup2 as ContravariantCup<Stick>).stirCoffee(ForkStick())

            // And if we actually want to now input only a Fork this work with covariant types
            val contravariantCup3: ContravariantCup<ForkStick> = contravariantCup2
            // but this is only because that the compiler made an upper-cast of inCup2, making it now of type of InCup<ForkStick>

            // Because of this, this will not work:
            // val inCup4: InCup<SpoonStick> = inCup3

            // but this will
            (contravariantCup3 as ContravariantCup<SpoonStick>).stirCoffee(SpoonStick())
            val contravariantCup4: ContravariantCup<SpoonStick> = contravariantCup3

            // This is how we typically see contravariance
            // It makes sense and mostly we don't even think about it
            // However this is purely what contravariance means in coding.
            val contravariantCup5: ContravariantCup<SpoonStick> = ContravariantCup<Stick>()

            // This will never work.
            // Using a ForkStick in this case is not permitted, which means that a reference to Stick doesn't make sense
            // This is the reason why the compiler fails in this case
            // val inCup6: InCup<Stick> = InCup<SpoonStick>()
        }
    }
}