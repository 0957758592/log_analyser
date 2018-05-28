package com.danilov.loganalyzer;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LogAnalyzer {
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.US);

    public LogAnalyzer(){

    }

    public List<Token> getCollection(String path, LocalDateTime timeFrom, LocalDateTime timeTo) throws IOException {
        File pathToFile = new File(path);
        List<Token> tokens = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile), "UTF-8"))) {

            String strLine;
            while ((strLine = br.readLine()) != null) {
                LocalDateTime currentTime = LocalDateTime.parse(strLine.substring(strLine.indexOf("[") + 1, strLine.indexOf("]")), DATE_TIME_FORMATTER);
                if (timeFrom.isBefore(currentTime) && timeTo.isAfter(currentTime)) {
                    tokens.add(new Token(getMessage(strLine), getHttpMethod(strLine), getTime(strLine)));
                }
            }
        }
        return tokens;
    }

    public LocalDateTime getTime(String strLine) {
        return LocalDateTime.parse(strLine.substring(strLine.indexOf("[") + 1, strLine.indexOf("]")), DATE_TIME_FORMATTER);
    }

    public String getMessage(String strLine) {
        return strLine.substring(strLine.indexOf("\"") + 4);
    }

    public HttpMethod getHttpMethod(String s) {
        return s.toUpperCase().contains(HttpMethod.GET.getName()) ? HttpMethod.GET : HttpMethod.POST;
    }
}
