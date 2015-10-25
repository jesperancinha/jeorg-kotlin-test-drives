package com.steelzack.image.sizer;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.net.MalformedURLException;
import java.net.URL;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.kohsuke.args4j.CmdLineException;

public class ImageSizerWorkerTest {

	private Mockery context = new Mockery() {
		{
			setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	@Test
	public void testTransformImage() throws Exception {

		final ImageSizerOptions mockOptions = context.mock(ImageSizerOptions.class);
		final String destinationFile = "/tmp/result.jpg";
		final URL testSourceUrl = getClass().getResource("test_image_1.jpg");
		final String testDimensions = "100x100";

		context.checking(new Expectations() {
			{
				oneOf(mockOptions).getDestinationFile();
				will(returnValue(destinationFile));

				oneOf(mockOptions).getSourceFile();
				will(returnValue(testSourceUrl.toExternalForm()));

				oneOf(mockOptions).getDimensions();
				will(returnValue(testDimensions));
			}
		});

		final ImageSizerWorker imageSizerWorker = new ImageSizerWorker(null) {
			@Override
			void parseArguments(String[] args, ImageSizerOptions options) throws CmdLineException {
				assertSame(mockOptions, options);
				assertNull(args);
			}

			@Override
			URL getSourceFileUrl() throws MalformedURLException {
				return testSourceUrl;
			}

			@Override
			ImageSizerOptions getOptions() {
				return mockOptions;
			}
		};

		imageSizerWorker.transformImage();

	}

}
