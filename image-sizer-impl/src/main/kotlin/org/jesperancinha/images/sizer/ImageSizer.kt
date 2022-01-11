package org.jesperancinha.images.sizer

import org.jesperancinha.images.sizer.objects.ImageSizerType
import org.kohsuke.args4j.CmdLineParser


/**
 *
 * @author JOAO
 */
object ImageSizer {
    @JvmStatic
    fun main(args: Array<String>) {
        val options = ImageSizerOptions()
        parseArguments(args, options)
        val dimensions = options.dimensions
        val dims = dimensions?.split("x")?.toTypedArray() ?: throw RuntimeException("Please provide dimensions")
        val dotSplitsDestinationFileName = options.destinationFile!!.split("\\.").toTypedArray()
        val worker = ImageSizerWorker(
            destinationFile = options.destinationFile,
            sourceFile = options.sourceFile ?: throw RuntimeException("Please provide Source sile"),
            width = dims[0].toInt(),
            height = dims[1].toInt(),
            imageType = ImageSizerType.getImageSizerType(
                dotSplitsDestinationFileName[dotSplitsDestinationFileName.size - 1]
            ),
            desiredFormat = null
        )
        worker.processImage()
    }

    private fun parseArguments(args: Array<String>, options: ImageSizerOptions?) {
        val clp = CmdLineParser(options)
        clp.parseArgument(*args)
    }
}