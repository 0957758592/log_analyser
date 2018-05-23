package com.danilov.loganalyzer;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LogAnalyzer {

    public static void main(String[] args) throws IOException {
        String path = "I:/log.txt";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MMM/yyyy:HH:mm:ss", Locale.US);
        LocalDateTime timeFrom = LocalDateTime.parse("07/Mar/2004:16:23:12", format);
        LocalDateTime timeTo = LocalDateTime.parse("07/Mar/2004:17:17:27", format);
        getCollection(path, timeFrom, timeTo).toString();
    }

    public static List<Token> getCollection(String path, LocalDateTime timeFrom, LocalDateTime timeTo) throws IOException {
        File pathToFile = new File(path);
        List<Token> tokens = new ArrayList<>();

        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(pathToFile));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MMM/yyyy:HH:mm:ss", Locale.US);
                LocalDateTime currentTime = LocalDateTime.parse(strLine.substring(strLine.indexOf("[")+1, strLine.lastIndexOf("-")-1), format);
                 if (timeFrom.isBefore(currentTime) && timeTo.isAfter(currentTime)) {
                    String message = strLine.substring(strLine.indexOf("\""));
                    LocalDateTime time = LocalDateTime.parse(strLine.substring(strLine.indexOf("[")+1, strLine.lastIndexOf("-")-1), format);
                    tokens.add(new Token(message, getHttpMethod(strLine), time));
                }
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return tokens;
    }

    private static HttpMethod getHttpMethod(String s) {
        return s.contains(HttpMethod.GET.getName())? HttpMethod.GET : HttpMethod.POST;
    }

    private static class Token {
        private String message;
        private HttpMethod method;
        private LocalDateTime time;

        private Token(String message, HttpMethod method, LocalDateTime time) {
            this.message = message;
            this.method = method;
            this.time = time;
        }
    }
}
