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
class FinanceVerticalTest(
    val context: VertxTestContext
) {

    init {
        context.completeNow()
    }

    @AfterEach
    fun tearDown() {
        context.completeNow()
    }

    @Test
    fun testMyApplication() {
        val vertx = Vertx.vertx()
        vertx.also {
            it.deployVerticle(FinanceVerticle(), DeploymentOptions())
                .onComplete { result ->
                    if (result.succeeded()) {
                        println("Deployed: ${result.result()}")
                        vertx.createHttpClient().request(HttpMethod.GET, 8080, "localhost", "/")
                            .compose { request -> request.send() }
                            .onSuccess { response ->
                                response.body()
                                    .onSuccess { body ->
                                        println("Received: ${body.toString("UTF-8")}")
                                        vertx.close()
                                        context.completeNow()
                                    }
                                    .onFailure { err ->
                                        context.failNow(err)
                                    }
                            }
                            .onFailure { err ->
                                context.failNow(err)
                            }
                    } else {
                        println("Failed: ${result.cause()}")
                    }
                }
        }
    }
}