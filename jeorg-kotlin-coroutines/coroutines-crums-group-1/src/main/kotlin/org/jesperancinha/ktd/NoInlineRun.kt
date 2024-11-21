import kotlinx.coroutines.runBlocking

inline fun runSpringCoroutine(
    crossinline before: () -> Unit,
    noinline action: () -> Unit
) {
    before()
    action()
}

class NoInlineRun {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            runSpringCoroutine(
                before = { println("Before!") },
                action = { println("Running Coroutine!") }
            )
        }
    }
}