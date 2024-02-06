package org.jesperancinha.ktd.crums1.crum19

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ReadWritePropertyInterface {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            ConsolerizerComposer.outSpace()
                .cyan(ConsolerizerComposer.title("Crum 19 - ReadWriteProperty interface"))
            val lifestyle = Lifestyle("Vida loca")
            ConsolerizerComposer.outSpace()
                .green("Results lifestyle")
                .orange(lifestyle.lifestyle)
                .green("Results lifestyleKeeper")
                .orange(lifestyle.lifestyleKeeper)
                .reset()
        }
    }
}

class Lifestyle() {
    var lifestyle: String by LifestyleDelegate()

    var lifestyleKeeper: String = ""

    constructor(lifestyle: String) : this() {
        this.lifestyle = lifestyle
    }
}

class LifestyleDelegate : ReadWriteProperty<Lifestyle, String> {
    override fun getValue(thisRef: Lifestyle, property: KProperty<*>): String {
        ConsolerizerComposer.outSpace()
            .green("getting")
            .magenta(thisRef)
            .magenta(property)
            .magenta(property.name)
        return thisRef.lifestyleKeeper
    }

    override fun setValue(thisRef: Lifestyle, property: KProperty<*>, value: String) {
        ConsolerizerComposer.outSpace()
            .green("setting")
            .magenta(thisRef)
            .magenta(property)
            .magenta(property.name)
            .magenta(value)
        thisRef.lifestyleKeeper = value
    }

}