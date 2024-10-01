package org.jesperancinha.ktd.crums3.arroweffects

import arrow.core.left
import arrow.core.raise.Effect
import arrow.core.raise.effect
import arrow.core.raise.ensure
import arrow.core.raise.ensureNotNull
import arrow.core.raise.fold
import arrow.core.raise.toEither
import arrow.core.raise.toIor
import arrow.core.right
import arrow.core.toOption
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import java.io.File
import java.io.FileNotFoundException


fun readFile(path: String?): Effect<FileError, Content> = effect {
    ensureNotNull(path) { EmptyPath }
    ensure(path.isNotEmpty()) { EmptyPath }
    try {
        val lines = File(path).readLines()
        Content(lines)
    } catch (e: FileNotFoundException) {
        println(e)
        raise(FileNotFound(path))
    } catch (e: SecurityException) {
        raise(SecurityError(e.message))
    }
}

fun readFile2(path: String?): Effect<EmptyPath, Content> = effect {
    ensureNotNull(path) { EmptyPath }
    ensure(path.isNotEmpty()) { EmptyPath }
    Content(listOf("Not errors found and this is why this one is incomplete"))
}

@JvmInline
value class Content(val body: List<String>)

sealed interface FileError
@JvmInline
value class SecurityError(val msg: String?) : FileError
@JvmInline
value class FileNotFound(val path: String) : FileError
object EmptyPath : FileError {
    override fun toString() = "EmptyPath"
}

class ArrowFileEffects {
    companion object {

        private val logger = object {
            fun info(logText: Any?) = ConsolerizerComposer.out().cyan(logText)
            fun info2(logText: Any?) = ConsolerizerComposer.out().blue(logText)
            fun info3(logText: Any?) = ConsolerizerComposer.out().green(logText)
            fun infoTitle(logText: String) = ConsolerizerComposer.outSpace()
                .yellow(ConsolerizerComposer.title(logText))
        }

        @JvmStatic
        suspend fun main(args: Array<String> = emptyArray()) {
            logger.infoTitle("Crum 7 - Effects in Arrow from https://arrow-kt.io/docs/apidocs/arrow-core/arrow.core.raise/-effect/")
            logger.info(readFile("").toEither())
            logger.info(readFile("knit.properties").toEither())
            logger.info(readFile("knit.properties").toEither().left())
            logger.info(readFile("knit.properties").toEither().right())
            logger.info3(readFile("memorycard1.txt").toEither())
            logger.info3(readFile("memorycard1.txt").toEither())
            logger.info3(readFile("memorycard1.txt").toEither().left())
            logger.info3(readFile("memorycard1.txt").toEither().right())
            logger.info3(readFile("memorycard1.txt").toEither().getOrNull()?.body?.get(0))
            logger.info(readFile("gradle.properties").toIor())
            logger.info(readFile("README.MD").toOption())
            logger.info(readFile("build.gradle.kts").fold({ _: FileError -> null }, { it }))
            logger.info2(readFile2("").toEither())
            logger.info2(readFile2("knit.properties").toEither())
            logger.info2(readFile("memorycard2.txt").toEither())
            logger.info2(readFile("memorycard2.txt").toEither().getOrNull()?.body?.get(0))
            logger.info2(readFile("memorycard2.txt").toEither())
            logger.info2(readFile2("gradle.properties").toIor())
            logger.info2(readFile2("README.MD").toOption())
            logger.info2(readFile2("build.gradle.kts").fold({ _: FileError -> null }, { it }))
        }
    }
}