package org.jesperancinha.ktd.crums1

interface A {
    fun show() {
        println("Interface A")
    }
}

interface B {
    fun show() {
        println("Interface B")
    }
}

class C : A, B {
    override fun show() {
        println("In class C:")
        super<A>.show()
        super<B>.show()
    }
}

class SuperInterfaces {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
                val obj = C()
                obj.show()
        }
    }
}