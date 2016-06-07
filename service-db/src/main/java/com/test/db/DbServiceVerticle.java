package com.test.db;

import com.test.db.Utils.Holder;
import com.test.db.impl.DatabaseImpl;

import io.vertx.core.AbstractVerticle;
import io.vertx.serviceproxy.ProxyHelper;

/**
 * Created by budshrestha on 12/1/15.
 */
public class DbServiceVerticle extends AbstractVerticle {
    @Override
    public void start(){
        Holder.getInstance().setVertx(vertx);
        System.out.println("Database verticle started.");
        DatabaseImpl service = new DatabaseImpl();
        ProxyHelper.registerService(DbService.class, vertx, service, "vertx.Database");
    }
}
