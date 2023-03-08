package org.jesperancinha.ktd.rest

import kotlinx.coroutines.delay
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("test")
class SimpleRestController {

    @GetMapping
    suspend fun getTest() {
        delay(1000)
    }
}
