package com.steelzack.image.sizer;

/**
 * 
 * @author JOAO
 *
 */
public class ImageSizer {
	public static void main(String[] args) throws Exception {
		final ImageSizerWorker worker = new ImageSizerWorker(args);
		worker.transformImage();
	}
}