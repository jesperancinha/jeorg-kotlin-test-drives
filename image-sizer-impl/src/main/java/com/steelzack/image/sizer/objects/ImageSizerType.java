package com.steelzack.image.sizer.objects;

import java.text.MessageFormat;

public enum ImageSizerType { //
	BMP, //
	PNG, //
	TIFF, //
	JPG //
	;

	public static ImageSizerType getImageSizerType(String type) {
		switch (type) {
		case "bmp":
			return ImageSizerType.BMP;
		case "png":
			return ImageSizerType.PNG;
		case "tiff":
			return ImageSizerType.TIFF;
		case "jpg":
			return ImageSizerType.JPG;
		default:
			break;
		}
		throw new IllegalArgumentException(MessageFormat.format("Type {0} is invalid!", type));
	}
}
