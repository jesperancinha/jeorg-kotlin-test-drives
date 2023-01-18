package org.jesperancinha.votd

import io.vertx.core.AsyncResult
import io.vertx.core.Vertx
import io.vertx.core.http.HttpClientRequest
import io.vertx.core.http.HttpMethod
import io.vertx.junit5.VertxExtension
import io.vertx.junit5.VertxTestContext
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.function.Function

/**
 * Created by joao on 21-2-16.
 */
@ExtendWith(VertxExtension::class)
class FinanceVerticleTest {
    private var vertx: Vertx? = null
    @BeforeEach
    fun setUp(context: VertxTestContext) {
        vertx = Vertx.vertx()
        vertx.deployVerticle(FinanceVerticle::class.java.name, context.succeedingThenComplete())
    }

    @AfterEach
    fun tearDown() {
        vertx!!.close()
    }

    @Test
    fun testMyApplication(context: VertxTestContext) {
        val async: Async = context.async()
        vertx!!.createHttpClient().request(
            HttpMethod.GET, 8080, "localhost", "/"
        ) { response: AsyncResult<HttpClientRequest?> ->
            response.map(
                Function<HttpClientRequest?, Any?> { body: HttpClientRequest? ->
//                context.assertTrue(body.toString().contains("This is only the begining of cashing in"));
                    async.complete()
                    null
                })
        }
    }
}