package org.jesperancinha.ktd

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class CoffeesLauncher
fun main(args: Array<String>) {
    SpringApplication.run(CoffeesLauncher::class.java, *args).start()
}