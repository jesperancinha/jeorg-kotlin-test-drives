package com.jesperancinha.performance.test.jumpsearch.interfaces

import java.io.InputStream

interface JumpSearchFileStreams {
    fun getNumberIndexFromArray(number: Int, completeList: InputStream): Int
}