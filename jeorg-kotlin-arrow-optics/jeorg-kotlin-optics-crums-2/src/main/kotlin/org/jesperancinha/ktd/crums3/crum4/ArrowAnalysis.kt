package org.jesperancinha.ktd.crums3.crum4

//import arrow.analysis.pre
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

//fun encodeName(name: String): String {
//    pre(name.length == 10 && name.matches(Regex("[a-zA-Z]*"))) { "name may only have exactly 10 characters and may only contain letters" }
//    return name.map { it.minus('a') }.joinToString { "" }
//}
class ArrowAnalysis {
    companion object{

        private val logger = object {
            fun info(logText: Any?) = ConsolerizerComposer.out().orange(logText)
            fun info2(logText: Any?) = ConsolerizerComposer.out().red(logText)
            fun comment(logText: Any?) = ConsolerizerComposer.out().green(logText)
            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .brightMagenta(ConsolerizerComposer.title(logText))
        }

        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
//            logger.infoTitle("Crum 3 - Arrow Analysis")
//            logger.info(encodeName("Batatucada"))
        }
    }
}