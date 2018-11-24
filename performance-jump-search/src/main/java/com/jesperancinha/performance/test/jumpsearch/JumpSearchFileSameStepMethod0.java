package com.jesperancinha.performance.test.jumpsearch;

import com.performance.test.jumpsearch.interfaces.JumpSearchFile;

/**
 * This jump algorithm as described in:
 * http://www.stoimen.com/blog/2011/12/12/computer-algorithms-jump-search/ Tries
 * to calculate first generic step jump in order to search Unlike the binary
 * search model, it assumes that is better to jump a step and then perform the
 * already known binary search As for this implementation it is being created
 * without consideration on performance using an IJW (It just works) model This
 * is File0 for comparisons in performance evaluations for alternative
 * implementations
 * 
 * @author JOAO
 *
 */
public class JumpSearchFileSameStepMethod0 implements JumpSearchFile {
	public int getNumberIndexFromArray(int number, int[] completeList) {
		final int length = completeList.length;
		final int step = (int) Math.sqrt(length);
		int currentStep = 0;
		while (currentStep + step < length && completeList[currentStep + step] < number) {
			currentStep += step;
		}

		while (completeList[currentStep] < number && currentStep <= length) {
			currentStep++;
		}

		if (completeList[currentStep] == number) {
			return currentStep;
		}
		return -1;
	}
}
