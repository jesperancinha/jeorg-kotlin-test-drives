package org.jesperancinha.votd;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by joao on 21-2-16.
 */
@RunWith(VertxUnitRunner.class)
public class FinanceVerticleTest {

    private Vertx vertx;

    @Before
    public void setUp(TestContext context){
        vertx = Vertx.vertx();
        vertx.deployVerticle(FinanceVerticle.class.getName(), context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context){
        vertx.close();
    }

    @Test
    public void testMyApplication(TestContext context) {
        final Async async = context.async();

        vertx.createHttpClient().request(HttpMethod.GET,8080, "localhost", "/",
                response -> {
            response.map(body -> {
//                context.assertTrue(body.toString().contains("This is only the begining of cashing in"));
                async.complete();
                return null;
            });
        });
    }
}
