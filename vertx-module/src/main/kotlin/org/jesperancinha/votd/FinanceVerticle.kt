package org.jesperancinha.votd

import io.vertx.core.AbstractVerticle
import io.vertx.core.AsyncResult
import io.vertx.core.Promise
import io.vertx.core.http.HttpServer
import io.vertx.core.http.HttpServerRequest

/**
 * Created by joao on 21-2-16.
 */
class FinanceVerticle : AbstractVerticle() {
    @Throws(Exception::class)
    override fun start(startFuture: Promise<Void>) {
        vertx.createHttpServer().requestHandler { r: HttpServerRequest ->
            r.response().end("<p>This is only the begining of cashing in</p>")
        }
            .listen(8080) { result: AsyncResult<HttpServer?> ->
                if (result.succeeded()) {
                    startFuture.complete()
                } else {
                    startFuture.fail(result.cause())
                }
            }
    }
}