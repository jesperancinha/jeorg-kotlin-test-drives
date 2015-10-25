package com.steelzack.image.sizer;

import org.kohsuke.args4j.Option;

/**
 * 
 * @author JOAO
 *
 */
public class ImageSizerOptions {

	@Option(name = "-s", aliases = "--source-file", required = true, help = true)
	private String sourceFile;

	@Option(name = "-d", aliases = "--destination-file", required = true, help = true)
	private String destinationFile;

	@Option(name = "-dim", aliases = "--dimensions", usage = "<width>x<height>")
	private String dimensions;

	public String getSourceFile() {
		return sourceFile;
	}

	public String getDestinationFile() {
		return destinationFile;
	}

	public String getDimensions() {
		return dimensions;
	}
}
