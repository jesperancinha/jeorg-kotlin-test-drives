package com.jesperancinha.performance.test.jumpsearch;


import com.jesperancinha.performance.test.jumpsearch.benchmarking.JumpSearchFileSameStepMethod0BenchmarkingTest;
import com.jesperancinha.performance.test.jumpsearch.benchmarking.JumpSearchFileSameStepMethodStreams0BenchmarkingTest;
import com.jesperancinha.performance.test.jumpsearch.benchmarking.JumpSearchFileSameStepMethodStreams1BenchmarkingTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
		JumpSearchFileSameStepMethod0BenchmarkingTest.class,
		JumpSearchFileSameStepMethodStreams0BenchmarkingTest.class,
		JumpSearchFileSameStepMethodStreams1BenchmarkingTest.class //

})
public class AllBenchmarkingTests {

}
