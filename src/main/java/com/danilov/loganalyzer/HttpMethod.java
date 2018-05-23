package com.danilov.loganalyzer;

public enum HttpMethod {
    GET("GET"), POST("POST");
    String name;

    HttpMethod(String n) {
        name = n;
    }

    String getName() {
        return name;
    }
}
