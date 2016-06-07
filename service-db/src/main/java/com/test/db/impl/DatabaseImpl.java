package com.test.db.impl;

import com.test.db.DbService;
import com.test.db.Utils.NonSharedDatabase;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;


/**
 * Created by budshrestha on 12/1/15. email: shrestbuddha@gmail.com
 */
public class DatabaseImpl implements DbService {

    @Override
    public void storedProc(String procName, JsonObject databaseConfig, Handler<AsyncResult<JsonObject>> resultHandler) {
        String callStm = "{ call " + procName + " }";
        NonSharedDatabase.execute(callStm, databaseConfig, resultHandler);
    }

    @Override
    public void createStm(String query, JsonObject databaseConfig, Handler<AsyncResult<JsonObject>> resultHandler) {
        NonSharedDatabase.execute(query, databaseConfig, resultHandler);
    }

    @Override
    public void update(String query, JsonObject databaseConfig, Handler<AsyncResult<JsonObject>> resultHandler) {
        NonSharedDatabase.execute(query, databaseConfig, resultHandler);
    }

    @Override
    public void delete(String query, JsonObject databaseConfig, Handler<AsyncResult<JsonObject>> resultHandler) {
        NonSharedDatabase.execute(query, databaseConfig, resultHandler);
    }

    @Override
    public void read(String query, JsonObject databaseConfig, Handler<AsyncResult<JsonObject>> resultHandler) {
        NonSharedDatabase.query(query, databaseConfig, resultHandler);
    }

    @Override
    public void nonSharedRead(String query, JsonObject databaseConfig, Handler<AsyncResult<JsonObject>> resultHandler) {
        NonSharedDatabase.execute(query, databaseConfig, resultHandler);
    }
}
