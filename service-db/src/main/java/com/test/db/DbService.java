package com.test.db;


import com.test.db.impl.DatabaseImpl;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ProxyHelper;

/**
 * This is the interface for NuProcess Implementation. Created by pcjoshi on 11/27/15.
 */
@ProxyGen
@VertxGen
public interface DbService {
    static DbService create() {
        return new DatabaseImpl();
    }

    static DbService createProxy(Vertx vertx, String address) {
        return ProxyHelper.createProxy(DbService.class, vertx, address, new DeliveryOptions().setSendTimeout(432000000L));
    }

    void storedProc(String procName, JsonObject databaseConfig, Handler<AsyncResult<JsonObject>> resultHandler);

    void createStm(String query, JsonObject databaseConfig, Handler<AsyncResult<JsonObject>> resultHandler);

    void update(String query, JsonObject databaseConfig, Handler<AsyncResult<JsonObject>> resultHandler);

    void delete(String query, JsonObject databaseConfig, Handler<AsyncResult<JsonObject>> resultHandler);

    void read(String query, JsonObject databaseConfig, Handler<AsyncResult<JsonObject>> resultHandler);

    void nonSharedRead(String query, JsonObject databaseConfig, Handler<AsyncResult<JsonObject>> resultHandler);
}

