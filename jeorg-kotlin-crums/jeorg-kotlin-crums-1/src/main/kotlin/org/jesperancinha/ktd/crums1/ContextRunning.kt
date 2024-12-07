package org.jesperancinha.ktd.crums1

class Foo {
    fun foo() {
        print("Foo")
    }
}

context(Foo)
fun callFoo() {
    foo()
}


class ContextRunning {

    companion object{
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            with(Foo()) {
                callFoo()
            }
        }
    }
}