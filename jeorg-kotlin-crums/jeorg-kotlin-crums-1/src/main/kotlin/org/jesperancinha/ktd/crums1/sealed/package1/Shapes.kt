package org.jesperancinha.ktd.crums1.sealed.package1

sealed class Shape

data class Circle(val radius: Double) : Shape()
data class Rectangle(val width: Double, val height: Double) : Shape()


sealed class AnotherShape
