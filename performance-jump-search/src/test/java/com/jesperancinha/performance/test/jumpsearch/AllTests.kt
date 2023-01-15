package com.jesperancinha.performance.test.jumpsearch

import org.junit.platform.suite.api.SelectClasses
import org.junit.platform.suite.api.Suite

@Suite
@SelectClasses(AllBenchmarkingTests::class, AllSingleTests::class)
class AllTests 