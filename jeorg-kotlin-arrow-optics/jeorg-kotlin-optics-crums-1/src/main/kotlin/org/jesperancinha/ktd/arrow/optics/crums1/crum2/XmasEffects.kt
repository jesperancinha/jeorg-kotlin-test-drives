package org.jesperancinha.ktd.arrow.optics.crums1.crum2

import arrow.core.raise.Effect
import arrow.core.raise.getOrNull
import arrow.core.raise.toEither
import arrow.optics.optics
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.ktd.arrow.optics.crums1.crum2.Color.*
import org.jesperancinha.ktd.arrow.optics.crums1.crum2.PendulumType.MIDDLE_AGE
import java.time.LocalDateTime

private val logger = object {
    fun info(logText: Any?) = ConsolerizerComposer.out().brightRed(logText)
    fun info2(logText: Any?) = ConsolerizerComposer.out().brightGreen(logText)

    fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
        .brightWhite(ConsolerizerComposer.title(logText))
}


internal interface ICost {
    fun isExpensive(): Boolean = false
}

enum class Color(val hashColorValue: String) : ICost {
    WHITE("FFFFFF"),
    GREEN("008000"),
    YELLOW("FFFF00"),
    FUCSHSIA("FF00FF"),
    RED("FF0000"),
    SILVER("C0C0C0"),
    GOLD("FFD700"),
    SILVER_METAL("C0C0C0") {
        override fun isExpensive(): Boolean = true
    },
    GOLD_METAL("FFD700") {
        override fun isExpensive(): Boolean = true
    }
}

enum class PendulumType() {
    MIDDLE_AGE,
    MODERN,
    ULTRA_MODERN,
    MODEL_3000
}

data class Pendulum(
    val lengthCm: Int = 2,
    val color: Color = GOLD,
    val type: PendulumType = MIDDLE_AGE
)

@optics
sealed class Ball(
    val radiusCm: Int = 4,
    val color: Color = GOLD,
    val pendulum: Pendulum = Pendulum()
) {
    @optics
    data class AverageBall(val created: LocalDateTime = LocalDateTime.now()) : Ball() {
        companion object
    }

    @optics
    data class BigBall(val created: LocalDateTime = LocalDateTime.now()) : Ball(radiusCm = 8) {
        companion object
    }

    @optics
    data class SilverBall(val created: LocalDateTime = LocalDateTime.now()) : Ball(color = SILVER) {
        companion object
    }

    @optics
    data class ExpensiveSilverBall(val created: LocalDateTime = LocalDateTime.now()) : Ball(color = SILVER_METAL) {
        companion object
    }

    companion object
}

@optics
sealed class Garland(
    val lengthCm: Int = 10,
    val colors: List<Color> = listOf(GREEN),
    val radiusCm: Int = 2
) {

    @optics
    data class XmasEvolutionGarland(val created: LocalDateTime = LocalDateTime.now()) : Garland() {
        companion object
    }

    @optics
    data class CarnavalGarland(val created: LocalDateTime = LocalDateTime.now()) :
        Garland(lengthCm = 20, colors = listOf(GOLD, FUCSHSIA, YELLOW, RED, SILVER), radiusCm = 5) {
        companion object
    }

    companion object
}

@optics
sealed class XmasTree(
    balls: List<Ball> = emptyList(),
) {

    val treeConfig: Map<Int, List<Ball>> by lazy {
        balls.mapIndexed { i, ball ->
            if (i == 0) 1 to ball else i / 4 to ball
        }.groupBy { (level, _) -> level }
            .map { (level, listBallPairs) ->
                level to listBallPairs
                    .map { (_, balls) -> balls }
            }.toMap()
    }

    @optics
    data class EasyTree(
        val balls: List<Ball> = emptyList(),
        val garlands: List<Garland> = emptyList()
    ) : XmasTree(balls) {
        companion object
    }

    @optics
    data class BlackFridayTree(
        val created: LocalDateTime = LocalDateTime.now(),
        val balls: List<Ball> = listOf(Ball.AverageBall()),
        val garlands: List<Garland> = listOf(Garland.XmasEvolutionGarland())
    ) :
        XmasTree(balls) {
        companion object
    }

    @optics
    data class ExpensiveTree(
        val created: LocalDateTime = LocalDateTime.now(),
        val balls: List<Ball> = listOf(Ball.ExpensiveSilverBall()),
        val garlands: List<Garland> = listOf(Garland.XmasEvolutionGarland())
    ) :
        XmasTree(balls) {
        companion object
    }

    companion object
}


sealed interface XmasError

@JvmInline
value class ForbiddenMaterialsError(val color: Color) : XmasError

@JvmInline
value class InvalidTreeError(val size: Int) : XmasError

@JvmInline
value class TooMuchLevelsError(val levels: Int) : XmasError

/**
 * From top to bottom, a tree has 1 extra ball
 * Because it's a 3D model, each level will have the double of balls of the previous one
 * Single exception is the difference between the first and the second level where the first level always takes just one ball and the second 4
 * If:
 * 1. one floor does not have enough balls, then the tree gets unbalanced and we through InvalidTreeException
 * 2. No tree should be made of expensive materials at this stage. An attempt to do so should throw ForbiddenMaterialsException
 */
fun createTree(balls: List<Ball>, garlands: List<Garland> = emptyList()): Effect<XmasError, XmasTree> = arrow.core.raise.effect {
    try {
        balls.firstOrNull { ball: Ball -> ball.color.isExpensive() }?.takeIf { it.color.isExpensive() }
            ?.let { throw ForbiddenMaterialsException(it.color) }
        garlands.flatMap { it.colors }.firstOrNull { color: Color -> color.isExpensive() }?.takeIf { it.isExpensive() }
            ?.let { throw ForbiddenMaterialsException(it) }
        val levels = when {
            balls.size == 1 -> 1
            (balls.size - 1) % 4 == 0 -> (balls.size - 1) / 4 + 1
            else -> throw InvalidTreeException(balls.size)
        }
        if (levels > 10) throw TooMuchLevelsException(levels)
        XmasTree.EasyTree(balls, garlands)
    } catch (ex: ForbiddenMaterialsException) {
        raise(ForbiddenMaterialsError(ex.color))
    } catch (ex: InvalidTreeException) {
        raise(InvalidTreeError(ex.size))
    } catch (ex: TooMuchLevelsException) {
        raise(TooMuchLevelsError(ex.levels))
    }
}

class ForbiddenMaterialsException(val color: Color) : Exception()
class InvalidTreeException(val size: Int) : Exception()
class TooMuchLevelsException(val levels: Int) : Exception()
class XmasEffects {
    companion object {

        @JvmStatic
        suspend fun main(args: Array<String> = emptyArray()) {
            logger.infoTitle("Crum 2 - Combining Optics and Effects")

            val tree = XmasTree.BlackFridayTree()

            logger.info2("Creating a Black Friday Tree")
            tree.logLevel(1)

            logger.info2("Creating a one average Ball Tree")
            createTree(listOf(Ball.AverageBall())).getOrNull()?.logLevel(1)

            logger.info2("Creating a one expensive Ball Tree")
            val xmasErrorXmasTreeEffect = createTree(listOf(Ball.ExpensiveSilverBall()))
            xmasErrorXmasTreeEffect.getOrNull()?.logLevel(1)
            logger.info(xmasErrorXmasTreeEffect)
            logger.info(xmasErrorXmasTreeEffect.toEither())

            logger.info2("Turning an expensive tree into a normal tree")
            val expensiveTree = XmasTree.ExpensiveTree().also { it.logLevel(1) }
            val expensiveTreeLens = XmasTree.expensiveTree
            val expensiveToNormalPrism = expensiveTreeLens.lift { it.copy(balls = listOf(Ball.AverageBall())) }
            expensiveToNormalPrism(expensiveTree).logLevel(1)
        }

    }
}

private fun XmasTree.logLevel(level: Int) {
    logger.info(this)
    logger.info(this.treeConfig)
    for (i in 0 until level) {
        val treeConfiguration = this.treeConfig[i]
        logger.info(treeConfiguration)
        logger.info(treeConfiguration)
        treeConfiguration?.forEach { singleBall ->
            logger.info(singleBall)
            logger.info(singleBall.color)
            logger.info(singleBall.pendulum)
            logger.info(singleBall.radiusCm)
        }
    }
}
