package com.steelzack.image.sizer;

import static com.google.common.base.Preconditions.checkState;
import static java.awt.RenderingHints.KEY_INTERPOLATION;
import static java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR;
import static java.awt.Toolkit.getDefaultToolkit;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import com.steelzack.image.sizer.objects.ImageSizerType;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * @author JOAO
 *
 */
public class ImageSizerWorker {

	private String sourceFile;
	private String destinationFile;
	private String desiredFormat;
	private int width;
	private int height;
	private ImageSizerType imageType;

	public ImageSizerWorker() {
		// CDI
	};

	public ImageSizerWorker(String[] args) throws CmdLineException {
		final ImageSizerOptions options = getOptions();
		parseArguments(args, options);

		destinationFile = options.getDestinationFile();
		sourceFile = options.getSourceFile();
		final String dimensions = options.getDimensions();
		if (dimensions != null) {
			final String[] dims = dimensions.split("x");
			width = Integer.parseInt(dims[0]);
			height = Integer.parseInt(dims[1]);
		}
		final String[] dotSplitsDestinationFileName = destinationFile.split("\\.");
		imageType = ImageSizerType.getImageSizerType( //
				dotSplitsDestinationFileName[dotSplitsDestinationFileName.length - 1] //
		);

	}

	void transformImage() throws InterruptedException, IOException {
		final URL sourceFile = getSourceFileUrl();
		final File destinationFile = new File(getDestinationFile());
		checkState(!destinationFile.isDirectory(),
				MessageFormat.format("Destination file is a directory!-{0}", getDestinationFile()));
		if (destinationFile.getParentFile() != null) {
			destinationFile.getParentFile().mkdirs();
		}

		Image image = getDefaultToolkit().getImage(sourceFile);
		MediaTracker mediaTracker = new MediaTracker(new Container());
		mediaTracker.addImage(image, 0);
		mediaTracker.waitForID(0);

		int newImageSizeWidth = getWidth();
		int newImageSizeHeight = getHeight();

		final BufferedImage newImageSizeImage = new BufferedImage(newImageSizeWidth,
				newImageSizeHeight, TYPE_INT_RGB);
		final Graphics2D graphics2D = newImageSizeImage.createGraphics();
		graphics2D.setRenderingHint( //
				KEY_INTERPOLATION, //
				VALUE_INTERPOLATION_BILINEAR //
		);
		graphics2D.drawImage(image, 0, 0, newImageSizeWidth, newImageSizeHeight, null);

		saveToFile(destinationFile, newImageSizeImage);
	}

	private void saveToFile(final File destinationFile, final BufferedImage newImageSizeImage)
			throws IOException, FileNotFoundException {
		try (final BufferedOutputStream out = new BufferedOutputStream( //
				new FileOutputStream(destinationFile) //
		);) {
			final JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			final JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(newImageSizeImage);
			param.setQuality(1, false);
			encoder.setJPEGEncodeParam(param);
			encoder.encode(newImageSizeImage);
		}
	}

	public String getSourceFile() {
		return sourceFile;
	}

	public String getDestinationFile() {
		return destinationFile;
	}

	public String getDesiredFormat() {
		return desiredFormat;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ImageSizerType getImageType() {
		return imageType;
	}

	ImageSizerOptions getOptions() {
		return new ImageSizerOptions();
	}

	URL getSourceFileUrl() throws MalformedURLException {
		return new File(getSourceFile()).toURI().toURL();
	}

	void parseArguments(String[] args, final ImageSizerOptions options) throws CmdLineException {
		final CmdLineParser clp = new CmdLineParser(options);
		clp.parseArgument(args);
	}
}
