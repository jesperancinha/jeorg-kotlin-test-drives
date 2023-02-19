package org.jesperancinha.ktd.rest

import org.jesperancinha.ktd.domain.Coffee
import org.jesperancinha.ktd.service.CoffeeService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.*
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.publisher.Mono
import java.util.*


@RestController
@RequestMapping("/v1")
class CoffeeController(val coffeeService: CoffeeService) {
    @PostMapping("/coffees")
    @ResponseStatus(HttpStatus.CREATED)
    fun send(@RequestBody coffee: Coffee): Mono<Coffee> {
        logger.info("Received '{}'", coffee)
        coffeeService.publish(coffee)
        return Mono.just(coffee)
    }

    @GetMapping(path = ["/coffees"], produces = [TEXT_EVENT_STREAM_VALUE])
    fun consumer(): Flux<ServerSentEvent<Coffee>> {
        return Flux.create { sink: FluxSink<Coffee> ->
            val id = UUID.randomUUID()
            sink.onCancel { coffeeService.remove(id)  }
            coffeeService
                .subscribe(id) { t:Coffee -> sink.next(t)}
        }.map { coffee: Coffee ->
            ServerSentEvent
                .builder<Coffee>()
                .data(coffee)
                .event("goal")
                .build()

        }
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(CoffeeController::class.java)
    }
}