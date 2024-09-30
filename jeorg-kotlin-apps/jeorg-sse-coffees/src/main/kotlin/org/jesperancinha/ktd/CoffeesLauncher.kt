package org.jesperancinha.ktd

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Scope
import org.springframework.web.context.WebApplicationContext

@Scope(WebApplicationContext.SCOPE_REQUEST)
@SpringBootApplication
class CoffeesLauncher

fun main(args: Array<String>) {
    SpringApplication.run(CoffeesLauncher::class.java, *args).start()
}
