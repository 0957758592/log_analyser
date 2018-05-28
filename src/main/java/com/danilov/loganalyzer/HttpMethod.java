package com.danilov.loganalyzer;

public enum HttpMethod {
    GET("GET"), POST("POST");
    private String name;

    HttpMethod(String n) {
        this.name = n;
    }

    String getName() {
        return name;
    }
}
