package Utils;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.json.JsonObject;
import org.apache.log4j.Logger;

/**
 * Created by budshrestha on 11/26/15.
 */
public class Holder {

    private JsonObject config = null;
    private static Holder instance = null;
    private Vertx vertx = null;
    Logger logger = null;


    protected Holder() {
        // Exists only to defeat instantiation.
    }

    public static Holder getInstance() {
        if (instance == null) {
            instance = new Holder();
        }
        return instance;
    }

    public void setConfig(JsonObject config) {
        this.config = config;
    }

    public JsonObject getConfig() {
        return config;
    }

    public Vertx getVertx() {
        return vertx;
    }

    public void setVertx(Vertx vertx) {
        this.vertx = vertx;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Logger getLogger() {
        return logger;
    }

    public HttpClient getApiClient(){ return vertx.createHttpClient(new HttpClientOptions()
                                                                            .setSsl(false).setTrustAll(true).setKeepAlive(false));}

}
