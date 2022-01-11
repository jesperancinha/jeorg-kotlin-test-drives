package org.jesperancinha.images.sizer.objects

enum class ImageSizerType {
    BMP, PNG, TIFF, JPG;

    companion object {
        @JvmStatic
        fun getImageSizerType(type: String?): ImageSizerType {
            when (type) {
                "bmp" -> return BMP
                "png" -> return PNG
                "tiff" -> return TIFF
                "jpg" -> return JPG
                else -> {}
            }
            throw IllegalArgumentException("Type $type is invalid!")
        }
    }
}