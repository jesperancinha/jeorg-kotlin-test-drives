package org.jesperancinha.ktd.crums1.crum12

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class NullCoalescingOperatorAndVararg {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {

            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 12 - null coalescing operator and vararg"))
                .reset()

            val drink1 = getDrink(null)
            val drink2 = getDrink("lemon", "water")
            val drink3 = getDrink("whisky", "cola")

            ConsolerizerComposer.outSpace()
                .black()
                .bgOrange(drink1)
                .bgOrange(drink2)
                .bgOrange(drink3)
                .reset()

        }

        private fun getDrink(vararg ingredients: String?): List<String> {
            ConsolerizerComposer.outSpace().blue("Do you see that we cannot make varag null: %s ?", ingredients)
            return ingredients.map { "Here is ${it ?: "water"}" }
        }
    }
}