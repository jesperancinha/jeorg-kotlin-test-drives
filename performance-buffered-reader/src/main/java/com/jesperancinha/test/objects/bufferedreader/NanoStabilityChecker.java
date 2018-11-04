package com.jesperancinha.test.objects.bufferedreader;

import java.io.PrintStream;

/**
 * Used for testing the reliability of using nanoseconds in performance for unit
 * cases.
 * 
 * @author JOAO
 *
 */
public class NanoStabilityChecker {

	public long printElementTo(String sentence, PrintStream out) {
		long startMarker = System.nanoTime();
		out.println(sentence);
		return System.nanoTime() - startMarker;
	}
}
