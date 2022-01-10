package org.jesperancinha.images.sizer

import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import org.junit.jupiter.api.Test
import java.io.File

internal class ImageSizerWorkerTest {

    @Test
    fun `should transform Image`() {
        val pathname: String = System.getProperty("java.io.tmpdir") ?: throw RuntimeException("tmp folder not found!")
        print(pathname)
        pathname.shouldNotBeNull()
        val sourceFile = javaClass.getResource("/org/jesperancinha/images/sizer/test_image.JPG")
        sourceFile.shouldNotBeNull()
        ImageSizerWorker(
            sourceFile = sourceFile.file,
            destinationFile = File(pathname, "result.JPG").absolutePath,
            width = 100,
            height = 100
        ).transformImage().shouldBeTrue()
    }

}