package com.jesperancinha.string.paradigm.performance2;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class StringWrapper {

	byte[] bytes;

	public StringWrapper(String string) {
		try {
			bytes = string.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		String to;
		try {
			to = new String(bytes, "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			to = null;
		}
		return to;
	}

	public byte[] getBytes(int from, int to) {
		return Arrays.copyOfRange(bytes, from, to);
	}
}
