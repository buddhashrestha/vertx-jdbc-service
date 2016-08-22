package com.test.db.Utils;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;


/**
 * The difference between Database and NonSharedDatabase is this class implements createNonShared
 * JDBC client which does not use the connection from the pool of existing JDBC connections i.e it
 * creates a different connection, everytime to execute database related operations. Created by
 * budshrestha on 12/20/15.
 */
public class NonSharedDatabase {
    public static void execute(String sql, JsonObject databaseConfig, Handler<AsyncResult<JsonObject>> done) throws RuntimeException {

        JDBCClient client = JDBCClient.createNonShared(Holder.getInstance().getVertx(), databaseConfig);
        client.getConnection(connect -> {
            if (connect.failed())
                done.handle(Future.failedFuture(connect.cause()));

            SQLConnection connection = connect.result();

            String sqlStatement = sql.replace(Constants.SPECIAL_DELIMITER, Constants.ENCODED_SPECIAL_DELIMITER);
            String[] statements = sqlStatement.split(";");
            startTx(connection, beginTrans -> {
                if (beginTrans!=null)
                    done.handle(Future.failedFuture(beginTrans.cause()));

                for (String statement : statements) {
                    statement.replace(Constants.ENCODED_SPECIAL_DELIMITER,Constants.SPECIAL_DELIMITER);
                    connection.execute(statement, res -> {

                        if (res.failed()) {
                            done.handle(Future.failedFuture(res.cause()));
                        }
                    });
                }

                endTx(connection, commitTrans -> {
                    if (commitTrans!=null)
                        done.handle(Future.failedFuture(commitTrans.cause()));
                    else
                        done.handle(Future.succeededFuture(new JsonObject().put("message", "Sucessfully executed")));

                    connection.close(close -> {
                        client.close();
                        if (close.failed()) {
                            throw new RuntimeException(close.cause());
                        }
                    });
                });
            });
        });
    }

    public static void call(String sql, JsonObject databaseConfig, Handler<AsyncResult<JsonObject>> done) throws RuntimeException {

        JDBCClient client = JDBCClient.createNonShared(Holder.getInstance().getVertx(), databaseConfig);
        client.getConnection(connect -> {
            if (connect.failed())
                done.handle(Future.failedFuture(connect.cause()));

            SQLConnection connection = connect.result();
            connection.call(sql, result -> {
                if (result.succeeded()) {
                    if (result.result()==null)
                        done.handle(Future.succeededFuture(new JsonObject().put("message", "Sucessfully executed")));
                    else
                        done.handle(Future.succeededFuture(result.result().toJson()));
                } else
                    done.handle(Future.failedFuture(result.cause()));

                connection.close(kil -> {
                    client.close();
                    if (kil.failed()) {
                        throw new RuntimeException(kil.cause());
                    }
                });
            });
        });
    }

    public static void query(String sql, JsonObject databaseConfig, Handler<AsyncResult<JsonObject>> done) throws RuntimeException {

        JDBCClient client = JDBCClient.createNonShared(Holder.getInstance().getVertx(), databaseConfig);
        client.getConnection(connect -> {
            if (connect.failed())
                done.handle(Future.failedFuture(connect.cause()));

            SQLConnection connection = connect.result();
            connection.query(sql, result -> {
                if (result.succeeded()) {
                    if (result.result()==null)
                        done.handle(Future.succeededFuture(new JsonObject().put("message", "Sucessfully executed")));
                    else
                        done.handle(Future.succeededFuture(result.result().toJson()));
                } else
                    done.handle(Future.failedFuture(result.cause()));

                connection.close(kil -> {
                    client.close();
                    if (kil.failed()) {
                        throw new RuntimeException(kil.cause());
                    }
                });
            });
        });
    }


    private static void startTx(SQLConnection conn, Handler<AsyncResult<Throwable>> done) {
        conn.setAutoCommit(false, res -> {
            if (res.failed()) {
                done.handle((AsyncResult<Throwable>) res.cause());
            }
            done.handle(null);
        });
    }

    private static void endTx(SQLConnection conn, Handler<AsyncResult<Throwable>> done) {
        conn.commit(res -> {
            if (res.failed()) {
                done.handle((AsyncResult<Throwable>) res.cause());
            } else if (res.succeeded()) {
                done.handle(null);
            }
        });
    }
}

