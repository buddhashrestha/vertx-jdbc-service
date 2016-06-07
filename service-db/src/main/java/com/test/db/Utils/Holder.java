package com.test.db.Utils;

import io.vertx.core.Vertx;

/**
 * Created by budshrestha on 11/26/15.
 */


public class Holder {

    private static Holder instance = null;
    private Vertx vertx = null;

    protected Holder() {
        // Exists only to defeat instantiation.
    }
    public static Holder getInstance() {
        if(instance == null) {
            instance = new Holder();
        }
        return instance;
    }

    public Vertx getVertx() {
        return vertx;
    }

    public void setVertx(Vertx vertx) {
        this.vertx = vertx;
    }

}