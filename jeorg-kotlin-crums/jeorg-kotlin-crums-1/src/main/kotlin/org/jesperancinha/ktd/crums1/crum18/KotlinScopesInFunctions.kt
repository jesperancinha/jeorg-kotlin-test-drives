package org.jesperancinha.ktd.crums1.crum18

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class KotlinScopesInFunctions {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 18 - Kotlin Scope Functions"))
                .magenta("There are 5 main scope functions: let, run, with, apply, also")
                .reset()

            val potato = "potato"

            ConsolerizerComposer.outSpace()
                .black().bgMagenta("let")
            val potatoLet = potato.let {
                ConsolerizerComposer.outSpace()
                    .none()
                    .green("This is the enclosing object").blue(this).newLine()
                    .green("This is not a potato").blue(toString())
                    .green("This is a potato").blue(it)
                    .newLine()
                    .reset()
                it + "let"
            }
            ConsolerizerComposer.outSpace().none()
                .magenta("This is the result").orange(potatoLet)
                .newLine()
                .reset()

            ConsolerizerComposer.outSpace()
                .black().bgMagenta("run")
            val potatoRun = potato.run {
                ConsolerizerComposer.outSpace()
                    .none()
                    .green("This is a potato").blue(this).newLine()
                    .green("This is a potato").blue(toString())
                    .newLine()
                    .reset()
                this + "run"
            }
            ConsolerizerComposer.outSpace().none()
                .magenta("This is the result").orange(potatoRun)
                .newLine()
                .reset()

            ConsolerizerComposer.outSpace()
                .black().bgMagenta("with")
            val potatoWith = with(potato) {
                ConsolerizerComposer.outSpace()
                    .none()
                    .green("This is a potato").blue(this).newLine()
                    .green("This is a potato").blue(toString())
                    .newLine()
                    .reset()
                this + "with"
            }
            ConsolerizerComposer.outSpace().none()
                .magenta("This is the result").orange(potatoWith)
                .newLine()
                .reset()

            ConsolerizerComposer.outSpace()
                .black().bgMagenta("apply")
            val potatoApply = potato.apply {
                ConsolerizerComposer.outSpace()
                    .none()
                    .green("This a potato").blue(this).newLine()
                    .green("This is a potato").blue(toString())
                    .newLine()
                    .reset()
                this + "apply"
            }
            ConsolerizerComposer.outSpace().none()
                .magenta("This is the result").orange(potatoApply)
                .newLine()
                .reset()

            ConsolerizerComposer.outSpace()
                .black().bgMagenta("also")
            val potatoAlso = potato.also {
                ConsolerizerComposer.outSpace()
                    .none()
                    .green("This is the enclosing object").blue(this).newLine()
                    .green("This is not a potato").blue(toString())
                    .green("This is a potato").blue(it)
                    .newLine()
                    .reset()
                it + "also"
            }
            ConsolerizerComposer.outSpace().none()
                .magenta("This is the result").orange(potatoAlso)
                .newLine()
                .reset()


        }
    }
}