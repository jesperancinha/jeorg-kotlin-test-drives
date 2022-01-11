package org.jesperancinha.images.sizer

import org.jesperancinha.images.sizer.objects.ImageSizerType
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.net.URL
import java.nio.file.Path
import javax.imageio.ImageIO
import kotlin.io.path.extension


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

    fun transformImage(): Boolean {
        val sourceFile = sourceFileUrl
        val destinationFile = File(destinationFile)
        if (destinationFile.isDirectory)
            throw RuntimeException("Destination file is a directory!-$destinationFile")
        if (destinationFile.parentFile != null) {
            destinationFile.parentFile.mkdirs()
        }
        val image: Image = ImageIO.read(File(sourceFile.toURI()))
        val scaledInstance =
            image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        val bufferedImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val graphics = bufferedImage.graphics
        return try {
            graphics.drawImage(scaledInstance, 0, 0, null)
            ImageIO.write(bufferedImage, Path.of(sourceFileUrl.toURI()).extension, destinationFile)
        } catch (e: IOException) {
            e.printStackTrace()
        }.let { true }
    }
}