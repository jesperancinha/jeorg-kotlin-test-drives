package org.jesperancinha.ktd.crums1

class Operators {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            Addition.main()
            CompoundAddition.main()
            StructuralEquality.main()
        }
        class Addition {
            companion object {
                data class Point(val x: Int, val y: Int) {
                    operator fun plus(other: Point): Point {
                        return Point(this.x + other.x, this.y + other.y)
                    }
                }

                @JvmStatic
                fun main(args: Array<String> = emptyArray()) {
                    val p1 = Point(2, 3)
                    val p2 = Point(4, 5)
                    println(p1 + p2)
                }

            }
        }

        class CompoundAddition {
            companion object {
                class Counter(var value: Int) {
                    operator fun plusAssign(other: Counter) {
                        this.value += other.value
                    }
                }

                @JvmStatic
                fun main(args: Array<String> = emptyArray()) {
                    val c1 = Counter(10)
                    val c2 = Counter(20)
                    c1 += c2
                    println(c1.value)
                }

            }
        }

        class StructuralEquality {
            companion object {
                data class Person(val name: String, val age: Int) {
                    override fun equals(other: Any?): Boolean {
                        if (other is Person) {
                            return this.name == other.name && this.age == other.age
                        }
                        return false
                    }
                }

                @JvmStatic
                fun main(args: Array<String> = emptyArray()) {
                    val p1 = Person("Alice", 30)
                    val p2 = Person("Alice", 30)
                    println(p1 == p2)
                }

            }
        }

    }
}