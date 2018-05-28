package com.danilov.loganalyzer;

import java.time.LocalDateTime;

public class Token {
    private String message;
    private HttpMethod method;
    private LocalDateTime time;

    public Token(String message, HttpMethod method, LocalDateTime time) {
        this.message = message;
        this.method = method;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public LocalDateTime getTime() {
        return time;
    }
}

