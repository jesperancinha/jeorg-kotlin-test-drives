package org.jesperancinha.ktd

import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class IOCoroutineLauncherTest {
    @Test
    fun `should run Main until the end`() = runBlocking {
        IOCoroutineLauncher.runAllTests()
    }
}