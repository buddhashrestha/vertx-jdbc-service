package com.test.db;


import io.vertx.core.json.JsonObject;

/**
 * Created by budshrestha on 12/11/14.
 */
public class Response {

    public static JsonObject getError(String msg){

        JsonObject resp = new JsonObject();
        resp.put("status","ERROR");
        resp.put("msg",msg);
        return resp;
    }

    public static JsonObject getSuccess(String msg){

        JsonObject resp = new JsonObject();
        resp.put("status","SUCCESS");
        resp.put("msg",msg);
        return resp;
    }

}
