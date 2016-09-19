package com.test.serviceConsumer;



import com.test.db.DbService;
import com.test.db.DbServiceVerticle;

import Utils.Holder;
import Utils.Runner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * Created by budshrestha on 06/06/16
 */
public class ConsumerVerticle extends AbstractVerticle {

    Logger logger = LoggerFactory.getLogger(ConsumerVerticle.class);

    public static void main(String[] args) {
        Runner.runExample(ConsumerVerticle.class);
        Runner.runExample(DbServiceVerticle.class);

    }


    @Override
    public void start(Future<Void> fut) {
        deployVerticle();
        startServer(vertx, fut);
        logger.info("========== Server started ==========12");
    }


    /**
     * Creating an HTTP server using the vertx instance.
     */
    public void startServer(Vertx vertx, Future<Void> fut) {
        Integer port = 8089;
        String url = "localhost";
        logger.info("Starting server at : " + port + " on url : " + url);
        Router router = Router.router(vertx);
        router.post("/execute").handler(this::handleReportReq);

        vertx.createHttpServer().requestHandler(router::accept).listen(port, url, result -> {
            if (result.succeeded()) {
                fut.complete();
                logger.info("Successfully deployed server at port :" + port);
            } else {
                fut.fail(result.cause());
            }
        });
    }

    /**
     * Deployment of all the required verticles must be done here. We are setting the options to
     * worker to run the tasks by the verticles at worker thread than the main thread.
     */
    public void deployVerticle() {
        DeploymentOptions options = new DeploymentOptions();
        options.setWorker(true);
        options.setConfig(vertx.getOrCreateContext().config());
//        vertx.deployVerticle("com.test.db.DbServiceVerticle", new DeploymentOptions().setWorker(true).setWorkerPoolName("dedicated").setInstances(1)
//        .setMaxWorkerExecuteTime(1000*100));
        vertx.deployVerticle("com.test.db.DbServiceVerticle");
    }

    private void handleReportReq(RoutingContext routingContext) {
        routingContext.response().putHeader("content-type", "application/json")
                .setStatusCode(200)
                .end(Json.encodePrettily(new JsonObject().put("message","got request!")));
        DbService dbService = DbService.createProxy(vertx, "vertx.Database");
        JsonObject databaseConfig = new JsonObject();
        databaseConfig.put("url", "jdbc:mysql://localhost:3306/namche")
                .put("driver_class", "com.mysql.jdbc.Driver")
                .put("max_pool_size", 30)
                .put("user", "root")
                .put("password", "root");
        dbService.storedProc("sleeping()", databaseConfig, result -> {
            if (result.succeeded())
                System.out.println("Completed!!"+ System.currentTimeMillis());
            else
                System.out.println("Failed!!");
        });
        JsonObject status = new JsonObject().put("status", "Completed");
        restRequest(status,postReq->{
            System.out.println("Successfully posted status.");
        });
    }

    private void restRequest(JsonObject requestBody, Handler<AsyncResult> syncher) {
        HttpClient apiClient = Holder.getInstance().getApiClient();
        apiClient.post(8080,
                       "192.169.1.3",
                       "api/post", response -> {
                    logger.info("Received response for statusPost Url with status code " + response.statusCode());
                    syncher.handle(null);//since we donot have lambda in this handler.
                }).putHeader("content-type", "text/plain")
                .end(Json.encodePrettily(requestBody));
    }
}
