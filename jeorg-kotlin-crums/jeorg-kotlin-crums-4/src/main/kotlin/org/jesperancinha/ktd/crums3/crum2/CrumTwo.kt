import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer

class CrumTwo {
    companion object {

        private val logger = object {
            fun info(logText: Any) = ConsolerizerComposer.out().white(logText)
            fun infoComment(logText: Any) = ConsolerizerComposer.out().green(logText)

            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .blue(ConsolerizerComposer.title(logText))
        }

        @JvmStatic
        fun main(args: Array<String>) {
            logger.infoTitle("Crum 2 - Prisms with code generation")

        }
    }
}
