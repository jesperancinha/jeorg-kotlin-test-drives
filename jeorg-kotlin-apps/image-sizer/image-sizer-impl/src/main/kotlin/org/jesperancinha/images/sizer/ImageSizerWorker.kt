package org.jesperancinha.images.sizer

import net.coobird.thumbnailator.Thumbnails
import org.jesperancinha.images.sizer.objects.ImageSizerType
import java.io.File
import java.net.URL


/**
 *
 * @author JOAO
 */
class ImageSizerWorker(
    val sourceFile: String,
    val destinationFile: String,
    val width: Int = 0,
    val height: Int = 0,
    private val imageType: ImageSizerType? = null,
    val desiredFormat: String? = null
) {
    private val sourceFileUrl: URL = File(sourceFile).toURI().toURL()

    fun processImage() {
        val outputFile = File(destinationFile)
        if (outputFile.isDirectory)
            throw RuntimeException("Destination file is a directory!-$outputFile")
        if (outputFile.parentFile != null) {
            outputFile.parentFile.mkdirs()
        }
        Thumbnails.of(sourceFile).width(width).height(height).toOutputStream(
            outputFile.outputStream()
        )
    }

}

