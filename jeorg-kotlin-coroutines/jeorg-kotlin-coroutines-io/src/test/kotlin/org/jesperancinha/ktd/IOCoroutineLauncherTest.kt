package org.jesperancinha.ktd

import kotlinx.coroutines.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class IOCoroutineLauncherTest {
    @Test
    fun `should run Main until the end`() = runBlocking {
        IOCoroutineLauncher.runAllTests()
    }
}