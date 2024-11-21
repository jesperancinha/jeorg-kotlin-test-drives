import kotlinx.coroutines.runBlocking

inline fun runSpringNoCoroutine(
    crossinline before: () -> Unit,
    action: () -> Unit
) {
    before()
    action()
}

inline fun runSpringCoroutine(
    crossinline before: () -> Unit,
    noinline action: suspend () -> Unit
) {
    before()
    runBlocking { action() }
}

class NoInlineRun {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            runSpringCoroutine(
                before = { println("Before!") },
                action = { println("Running Coroutine!") }
            )
            runSpringNoCoroutine(
                before = { println("Before!") },
                action = { println("Running Coroutine!") }
            )
        }
    }
}