package org.jesperancinha.ktd

import org.slf4j.Logger
import org.slf4j.LoggerFactory

val logger:Logger = LoggerFactory.getLogger(CupManager::class.java)

interface Stick {
    fun stir()
}

open class ForkStick : Stick {
    override fun stir() {
        logger.info("Stirring with a Fork Stick")
    }
}

open class SpoonStick : Stick {
    override fun stir() = logger.info("Stirring with a Spoon Stick")
}

class SoupSpoonStick : SpoonStick(){
    override fun stir() =  logger.info("Stirring with a Soup Spoon Stick")
}


class Cup<T:Stick> {
    fun makeCoffee(stick : T) = stick.stir()

}

class CupIn<in T:Stick> {
    fun makeCoffee(stick : T) = stick.stir()

}
class CupOut<out T:Stick>(
    private val stick : T
) {
    fun makeCoffee() = stick.stir().run { stick }

}

class CupManager {
    companion object {
        @JvmStatic
        fun main(args: Array<String> = emptyArray()) {
            logger.info(">>> Invariant Examples <<<")
            val cupInvariant = Cup<Stick>()
            cupInvariant.makeCoffee(ForkStick())
            logger.info(">>> Contravariance Examples <<<")

            logger.info(">>> Using a stick")
            val cupIn1 = CupIn<Stick>()
            cupIn1.makeCoffee(object : Stick {
                override fun stir() = logger.info("Stirring with a generic stick")

            })
            cupIn1.makeCoffee(SpoonStick())
            cupIn1.makeCoffee(SoupSpoonStick())

            logger.info(">>> Using a Stick")
            val cupIn2:CupIn<Stick> = cupIn1
            cupIn2.makeCoffee(SoupSpoonStick())
            cupIn2.makeCoffee(SpoonStick())
            cupIn2.makeCoffee(ForkStick())

            logger.info(">>> Using a Soup Spoon Stick")
            val cupIn3:CupIn<SoupSpoonStick> = cupIn2
            cupIn3.makeCoffee(SoupSpoonStick())

            logger.info(">>> Using a Fork Stick")
            val cupInSpoonStick:CupIn<SpoonStick> = cupIn1
            cupInSpoonStick.makeCoffee(SoupSpoonStick())
            cupInSpoonStick.makeCoffee(SpoonStick())
            //This won't work
//            cupInSpoonStick.makeCoffee(ForkStick())

            // This won't work
            logger.info(">>> Using a Soup Spoon Stick")
//            val cupInForkStick:CupIn<ForkStick> = cupInSpoonStick
//            cupIn3.makeCoffee(SoupSpoonStick())


            logger.info(">>> Covariance Examples <<<")
            var cupOut1 = CupOut<Stick>(object : Stick {
                override fun stir() =logger.info("Stirring with a generic stick")

            })
            cupOut1.makeCoffee()

            val cupOut2 = CupOut(SpoonStick())
            // Does not work with invariant types. Try removing `out` to see 😉
            cupOut1 = cupOut2
            cupOut1.makeCoffee()
            cupOut2.makeCoffee().also { println(it.javaClass) }
            val cupOut21: CupOut<Stick> = CupOut(SpoonStick())
            cupOut21.makeCoffee().also {
                val stick:SpoonStick = it as SpoonStick
                println(stick.javaClass)
            }

            val cupOut3:CupOut<SpoonStick> = CupOut(SoupSpoonStick())
            cupOut3.makeCoffee().also { println(it.javaClass) }
            val cupOut31: CupOut<SpoonStick> = CupOut(SoupSpoonStick())
            cupOut31.makeCoffee().also {
                val stick:SpoonStick = it as SoupSpoonStick
                println(stick.javaClass)
            }

        }
    }
}