package com.jesperancinha.performance.test.jumpsearch;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.performance.test.jumpsearch.benchmarking.JumpSearchFileSameStepMethod0BenchmarkingTest;
import com.performance.test.jumpsearch.benchmarking.JumpSearchFileSameStepMethodStreams0BenchmarkingTest;
import com.performance.test.jumpsearch.benchmarking.JumpSearchFileSameStepMethodStreams1BenchmarkingTest;

@RunWith(Suite.class)
@SuiteClasses({ //
		JumpSearchFileSameStepMethod0BenchmarkingTest.class, //
		JumpSearchFileSameStepMethodStreams0BenchmarkingTest.class, //
		JumpSearchFileSameStepMethodStreams1BenchmarkingTest.class //

})
public class AllBenchmarkingTests {

}
