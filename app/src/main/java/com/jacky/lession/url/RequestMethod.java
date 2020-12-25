package com.jacky.lession.url;



public enum RequestMethod {
    GET("GET"),POST("POST");

    private final String method;

    RequestMethod(String method) {
        this.method=method;
    }
    public String getName() {
        return method;
    }
}
