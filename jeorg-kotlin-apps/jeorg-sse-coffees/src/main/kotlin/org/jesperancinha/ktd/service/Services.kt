package org.jesperancinha.ktd.service

import org.jesperancinha.ktd.domain.Coffee
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer


@Service
class CoffeeService {
    private val consumers: MutableMap<UUID, Consumer<Coffee>> = mutableMapOf()
    fun subscribe(id: UUID, consumer: Consumer<Coffee>) {
        consumers[id] = consumer
        logger.info("Consumer with id {} subscribed! Total consumer number: {}", id, consumers.size)
    }

    fun publish(coffee: Coffee) {
        logger.info("Publishing coffee: {}", coffee)
        consumers.values.forEach { listener: Consumer<Coffee> ->
            listener.accept(coffee)
        }
    }

    fun remove(id: UUID) {
        consumers.remove(id)
        logger.info("Removed coffee consumer: {}", id)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(CoffeeService::class.java)
    }
}