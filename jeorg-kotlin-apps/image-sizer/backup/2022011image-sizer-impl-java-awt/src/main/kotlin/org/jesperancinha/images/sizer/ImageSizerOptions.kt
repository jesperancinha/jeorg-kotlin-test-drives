package org.jesperancinha.images.sizer

import org.kohsuke.args4j.Option

/**
 *
 * @author JOAO
 */
class ImageSizerOptions {
    @Option(name = "-s", aliases = ["--source-file"], required = true, help = true)
    val sourceFile: String? = null

    @Option(name = "-d", aliases = ["--destination-file"], required = true, help = true)
    val destinationFile: String? = null

    @Option(name = "-dim", aliases = ["--dimensions"], usage = "<width>x<height>")
    val dimensions: String? = null
}