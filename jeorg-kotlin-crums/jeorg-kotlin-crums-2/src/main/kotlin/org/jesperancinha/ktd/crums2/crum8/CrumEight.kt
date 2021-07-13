package org.jesperancinha.ktd.crums2.crum8

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

/**
 * Created by jofisaes on 09/07/2021
 */
class CrumEight {
    companion object {
        private val theThing = "The thing"

        @PublishedApi
        internal val theThingFixed = "The thing fixed"

        @JvmStatic
        fun main(args: Array<String>) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 8 - inline, noinline, crossinline"))
                .reset()

            nothingToInline {
                ConsolerizerComposer.out().magenta("Without inline, the javabyte code will be built as usual")
                return@nothingToInline
            }
            inlineAllTheThings {
                ConsolerizerComposer.out()
                    .magenta("This function will appear in the inlineAllTheThings method via JavaCode")
                return@inlineAllTheThings
                // return The return is possible, but this would return to main
            }
            excludingInlineAllTheThings ({
                ConsolerizerComposer.out()
                    .magenta("This function is inline and does not get excluded")
                return@excludingInlineAllTheThings
                // return The return is possible, but this would return to main

            }){
                ConsolerizerComposer.out()
                    .magenta("This function is excluded from inline and does get excluded")
                return@excludingInlineAllTheThings
            }

            nothingPossibleToReturn {
                ConsolerizerComposer.out()
                    .magenta("This function is inline")
                    .reset()
                return@nothingPossibleToReturn
                // return This one is not possible
            }

            ConsolerizerComposer.outSpace()
                .magenta("A few things to note on this exercise")
                .magenta("1. We can access the main scope using referenced scopes in Kotlin")
                .magenta("2. Because of this, we may want to limit the range of the scopes")
                .magenta("3. The reason for inline, noinline and crossinline is precisely because of this")


        }

        private fun nothingToInline(function: () -> Unit) {
            ConsolerizerComposer.outSpace()
                .orange("This will follow the default compilation algorithm")
                .blue(theThing)
                .reset()
            function()
        }

        inline fun inlineAllTheThings(function: () -> Unit) {
            ConsolerizerComposer.outSpace()
                .orange("You will find this in line via Java codebytes")
//                .blue(theThing)
                .blue(theThingFixed)
                .reset()
            function()
        }

        inline fun excludingInlineAllTheThings(function: () -> Unit, noinline niFunction:()->Unit) {
            ConsolerizerComposer.outSpace()
                .orange("You will find this in line via Java codebytes")
//                .blue(theThing)
                .blue(theThingFixed)
                .reset()
            function()
            niFunction()
        }

         inline fun nothingPossibleToReturn(crossinline function: () -> Unit) {
             ConsolerizerComposer.outSpace()
                 .orange("This will follow the default compilation algorithm")
                 .blue(theThingFixed)
                 .reset()
             function()
         }
    }
}