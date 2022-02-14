package org.jesperancinha.votd;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

/**
 * Created by joao on 21-2-16.
 */
public class FinanceVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startFuture) throws Exception {

        vertx.createHttpServer().requestHandler(r -> {
            r.response().end("<p>This is only the begining of cashing in</p>");
        }).listen(8080, result -> {
            if (result.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(result.cause());
            }
        });
    }
}
