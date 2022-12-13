import arrow.optics.Lens
import arrow.optics.optics

@optics
data class Account(val balance: Int, val available: Int) {
    companion object
}
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

//    val balanceLens: Lens<Account, Int> = Account.balance
}
