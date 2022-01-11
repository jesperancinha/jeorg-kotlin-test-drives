package org.jesperancinha.images.sizer

import io.kotest.matchers.nulls.shouldNotBeNull
import javafx.application.Platform
import org.junit.jupiter.api.Test
import java.io.File

internal class ImageSizerWorkerTest {

    @Test
    fun `should transform Image`() {
        val pathname: String = System.getProperty("java.io.tmpdir") ?: throw RuntimeException("tmp folder not found!")
        println(pathname)
        pathname.shouldNotBeNull()
        val sourceFile = javaClass.getResource("/org/jesperancinha/images/sizer/test_image.JPG")
        sourceFile.shouldNotBeNull()
        val destinationFileName = "result.png"
        Platform.startup {
            ImageSizerWorker(
                sourceFile = sourceFile.file,
                destinationFile = File(pathname, destinationFileName).absolutePath,
                width = 500,
                height = 100
            ).processImage()
        }
        println("$pathname$destinationFileName")
    }

}