import arrow.optics.Lens
import arrow.optics.optics

@optics
data class Account(val balance: Int, val available: Int) {
    companion object
}

fun main(args: Array<String>) {
    println("Hello World!")
    println("Program arguments: ${args.joinToString()}")

    val balanceLens: Lens<Account, Int> = Account.balance

    println(balanceLens)
}
