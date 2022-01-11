package org.jesperancinha.images.sizer

import javafx.embed.swing.SwingFXUtils
import javafx.embed.swing.SwingFXUtils.fromFXImage
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import org.jesperancinha.images.sizer.objects.ImageSizerType
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.net.URL
import javax.imageio.ImageIO
import javax.imageio.ImageIO.write


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

    private fun resize(source: Image, targetWidth: Int, targetHeight: Int, preserveRatio: Boolean): Image =
        ImageView(source).apply {
            isPreserveRatio = preserveRatio
            fitWidth = targetWidth.toDouble()
            fitHeight = targetHeight.toDouble()
        }.snapshot(null, null)

    fun processImage() {
        val outputFile = File(destinationFile)
        if (outputFile.isDirectory)
            throw RuntimeException("Destination file is a directory!-$outputFile")
        if (outputFile.parentFile != null) {
            outputFile.parentFile.mkdirs()
        }

        return try {
            val src = File(sourceFile)
            val image = Image(src.inputStream());
            val imageResult = resize(image, width, height, preserveRatio = false)
            val bufferedImage: BufferedImage = fromFXImage(imageResult, null)
            write(bufferedImage, "png", outputFile)
        } catch (e: IOException) {
            e.printStackTrace()
        }.let { true }
    }

}