package com.danilov.loganalyzer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class Test {
    public static void main(String[] args) throws IOException {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        String path = "I:/log.txt";
        final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss", Locale.US);
        LocalDateTime timeFrom = LocalDateTime.parse("07/Mar/2004:16:23:12", DATE_TIME_FORMAT);
        LocalDateTime timeTo = LocalDateTime.parse("07/Mar/2004:17:17:27", DATE_TIME_FORMAT);
        for (Token token : logAnalyzer.getCollection(path, timeFrom, timeTo)) {
            System.out.println(token.getTime());
            System.out.println(token.getMethod());
            System.out.println(token.getMessage());
        }
    }
}
