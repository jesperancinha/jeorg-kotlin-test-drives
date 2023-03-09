package org.jesperancinha.ktd

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class IOCoroutineLauncherTest {
    @Test
    fun `should run Main until the end`() = runBlocking {
        IOCoroutineLauncher.runAllTests()
    }
}