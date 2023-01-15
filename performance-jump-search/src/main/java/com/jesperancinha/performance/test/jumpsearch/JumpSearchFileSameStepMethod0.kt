package com.jesperancinha.performance.test.jumpsearch

import com.jesperancinha.performance.test.jumpsearch.interfaces.JumpSearchFile

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
 */
class JumpSearchFileSameStepMethod0 : JumpSearchFile {
    override fun getNumberIndexFromArray(number: Int, completeList: IntArray): Int {
        val length = completeList.size
        val step = Math.sqrt(length.toDouble()).toInt()
        var currentStep = 0
        while (currentStep + step < length && completeList[currentStep + step] < number) {
            currentStep += step
        }
        while (completeList[currentStep] < number && currentStep <= length) {
            currentStep++
        }
        return if (completeList[currentStep] == number) {
            currentStep
        } else -1
    }
}