package main; 

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class WriterIntoLogs{

    static public void printLog(String type, String fullName, String action, String title) throws IOException{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("files/logs.txt", true))) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);

            bw.write(type + " " + fullName + " " + action + " " + title + " at " + formattedDateTime);
            bw.newLine();
        } 
    }

    static public void printLog(String type, String fullName, String action) throws IOException{
         try (BufferedWriter bw = new BufferedWriter(new FileWriter("files/logs.txt", true))) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);

            bw.write(type + " " + fullName + " " + action + " at " + formattedDateTime);
            bw.newLine();
        } 
    }

}