package org.jesperancinha.votd

import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.junit5.VertxExtension
import io.vertx.junit5.VertxTestContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * Created by joao on 21-2-16.
 */
@ExtendWith(VertxExtension::class)
class FinanceVerticleTest constructor(
    context: VertxTestContext
) {

    init {
        context.completeNow()
    }

    private val vertx: Vertx by lazy {
        Vertx.vertx().also {
            it.deployVerticle(FinanceVerticle(), DeploymentOptions())
                .onComplete { result ->
                    if (result.succeeded()) {
                        println("Deployed: ${result.result()}")
                    } else {
                        println("Failed: ${result.cause()}")
                    }
                }
        }
    }

    @AfterEach
    fun tearDown(context: VertxTestContext) {
        context.completeNow()
    }

    @Test
    fun testMyApplication(context: VertxTestContext) {
        vertx.createHttpClient().request(HttpMethod.GET, 8080, "localhost", "/")
            .compose { request -> request.send() }
            .onSuccess { response ->
                response.body()
                    .onSuccess { body ->
                        println("Received: ${body.toString("UTF-8")}")
                        context.completeNow()
                    }
                    .onFailure { err ->
                        context.failNow(err)
                    }
            }
            .onFailure { err ->
                context.failNow(err)
            }
    }
}