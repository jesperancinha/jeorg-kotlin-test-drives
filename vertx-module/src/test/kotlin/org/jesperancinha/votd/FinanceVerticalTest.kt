package org.jesperancinha.votd

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
class FinanceVerticalTest constructor(
    context: VertxTestContext
) {

    private val vertx: Vertx by lazy {
        Vertx.vertx().also {
            it.deployVerticle(FinanceVerticle::class.java.name, context.succeedingThenComplete())
        }
    }

    @AfterEach
    fun tearDown(context: VertxTestContext) {
        context.completeNow()
        vertx.close()
    }

    @Test
    fun testMyApplication(context: VertxTestContext) {
        vertx.createHttpClient().request(
            HttpMethod.GET, 8080, "localhost", "/"
        ) { it.map {
//                context.assertTrue(body.toString().contains("This is only the begining of cashing in"));
                context.completeNow()
                null
            }
        }
    }
}