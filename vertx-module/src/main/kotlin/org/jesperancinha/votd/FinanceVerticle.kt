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
    override fun start(startPromise: Promise<Void>) {
        vertx.createHttpServer()
            .requestHandler { request: HttpServerRequest ->
                request.response().end("<p>This is only the beginning of cashing in</p>")
            }
            .listen(8080)
            .onComplete { result ->
                if (result.succeeded()) {
                    startPromise.complete()
                } else {
                    startPromise.fail(result.cause())
                }
            }
    }
}