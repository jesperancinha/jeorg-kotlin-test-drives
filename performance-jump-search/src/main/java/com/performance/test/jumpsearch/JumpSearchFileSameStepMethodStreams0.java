package com.performance.test.jumpsearch;

import com.performance.test.jumpsearch.interfaces.JumpSearchFile;

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
public class JumpSearchFileSameStepMethodStreams0 implements JumpSearchFile {

	@Override
	public int getNumberIndexFromArray(int number, int[] completeList) {
		// TODO Auto-generated method stub
		return 0;
	}

}
