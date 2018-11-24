package com.jesperancinha.performance.test.jumpsearch;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import com.jesperancinha.performance.test.jumpsearch.interfaces.JumpSearchFileStreams;

/**
 * For this implementation I decided to try a stream approach. All files have
 * been generated and so what we have to do is to provide a running algorithm
 * that, instead of retrieving and placing everything in memory. It will just
 * use the element it needs, and throw everything else away while doing so.
 * 
 * With this exercise I intend to check the differences in memory occupation and
 * in
 * 
 * @author JOAO
 */
public class JumpSearchFileSameStepMethodStreams1 implements JumpSearchFileStreams {

	@Override
	public int getNumberIndexFromArray(int number, InputStream completeList) {
		completeList.mark(Integer.MAX_VALUE);
		try (Scanner scannerCounter = new Scanner(completeList)) {
			scannerCounter.useDelimiter("\\,\\ ");
			int length = 0;
			while (scannerCounter.hasNextLine()) {
				scannerCounter.nextInt();
				length++;
			}
			completeList.reset();
			try (Scanner scanner = new Scanner(completeList)) {
				final StringBuilder sb = new StringBuilder();
				final int step = (int) Math.sqrt(length);
				for (int i = 0; i < step - 1; i++) {
					sb.append("[0-9]{1,}\\,\\ ");
				}
				sb.append("[0-9]{1,}");
				final String pattern = sb.toString();

				int currentStep = 0;
				while (scanner.hasNextLine()) {
					String values = scanner.findInLine(pattern);
					if (values == null) {
						values = scanner.nextLine();
					}
					final int[] segment = Arrays.asList(values.split(", ")).stream().mapToInt(Integer::parseInt)
							.toArray();
					currentStep += segment.length;
					if (segment[segment.length - 1] >= number) {
						currentStep -= step;
						for (int i = 0; i < step; i++) {
							if (segment[i] == number) {
								return currentStep;
							}
							currentStep++;
						}
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return -1;
	}

}
