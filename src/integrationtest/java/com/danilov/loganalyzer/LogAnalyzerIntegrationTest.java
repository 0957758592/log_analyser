package com.danilov.loganalyzer;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class LogAnalyzerIntegrationTest {
  private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss", Locale.US);
  private static final int INITIAL_CAPACITY = 10;
  private static LocalDateTime timeFrom;
  private static LocalDateTime timeTo;
  private static File dir;
  private static File file;
  private File html;

  private BufferedWriter bw;

  private String log;
  private String path;
  private LogAnalyzer logAnalyzer;
  private List<Token> tokens;

  private static StringBuilder sb = new StringBuilder();
  private static List<String> list = new ArrayList<>();

  @BeforeClass
  public static void beforeClass() throws IOException {
    timeFrom = LocalDateTime.parse("07/Mar/2004:16:23:12", DATE_TIME_FORMAT);
    timeTo = LocalDateTime.parse("07/Mar/2004:17:17:27", DATE_TIME_FORMAT);

    dir = new File("TEST_DIR");
    file = new File("TEST_DIR/testFile.txt");

    URL url =  new URL("http://www.monitorware.com/en/logsamples/apache.php");
    URLConnection connection = url.openConnection();
    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

    String line;
    while ((line = br.readLine()) !=null){
      sb.append(line);
    }
    br.close();

  }

  @Before
  public void before() throws IOException {
    logAnalyzer = new LogAnalyzer();
    log = "64.2!2.88.1! - - [07/Mar/2004:1!:10:!2 -0800] \"GET /mailman/listinfo/hsdivision HTTP/1.1\" 200 6291 !!!";
    log.replace('!', '5');

    if (!dir.exists()) {
      dir.mkdir();
    }

    if (!file.exists()) {
      file.createNewFile();
    }

    bw = new BufferedWriter(new FileWriter(file));
    for (int i = 0; i < INITIAL_CAPACITY; i++) {

      bw.write(log + "\n");
    }
    bw.close();



  }

  @Test
  public void getMessage() throws IOException {
//    BufferedReader br = new BufferedReader(new FileReader(file));

//    for (int i = 0; i < INITIAL_CAPACITY; i++) {
//      System.out.println("BR:  " + br.readLine());
//    }

//    br.close();

  }


  @Test
  public void getHttpLines() throws IOException {
    dir = new File("TEST_HTML");
    dir.mkdir();
    html = new File("TEST_HTML/testhtml.html");
    html.createNewFile();
    bw = new BufferedWriter(new FileWriter(html));
    sb.toString().replaceAll("\\<.*?>","");
    bw.write(sb.toString());
//    for (int i = 0; i < INITIAL_CAPACITY; i++) {
//      System.out.println("SITE: "+ sb.toString());
//    }

  }


  @Test
  public void getHTTP(){
    System.out.println(logAnalyzer.getHttpMethod(sb.toString()));
  }

  @Test
  public void getMessages(){
    System.out.println(logAnalyzer.getMessage(sb.toString()));
  }

  @Test
  public void getTime(){
    System.out.println(logAnalyzer.getTime(sb.toString()));
  }

}
