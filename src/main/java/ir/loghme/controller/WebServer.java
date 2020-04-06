package main.java.ir.loghme.controller;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class WebServer {
    private int port;
    private Javalin app;

    public WebServer(int port) {
        this.port = port;
    }

    public void start() {
        this.app = Javalin.create().start(port);
    }
    public void get(String path, Handler handler) {
        this.app.get(path,handler);

    }


}
