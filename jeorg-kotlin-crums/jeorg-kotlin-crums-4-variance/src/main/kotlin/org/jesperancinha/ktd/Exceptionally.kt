package org.jesperancinha.ktd


class OneWay {
    class NotEnoughSaldoException(mensagem: String = "Not enough credit") : Exception(mensagem)
}

class Another {
    class NotEnoughSaldoException(override val message: String = "Not enough credit") : Exception()
}

class Exceptionally {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            runCatching { throw OneWay.NotEnoughSaldoException() }
                .onFailure<OneWay.NotEnoughSaldoException> {
                    (it as OneWay.NotEnoughSaldoException).message
                    println(it.message)
                }
            runCatching { throw Another.NotEnoughSaldoException() }
                .onFailure<Another.NotEnoughSaldoException> {
                    (it as Another.NotEnoughSaldoException).message
                    println(it.message)
                }
        }
    }

}
