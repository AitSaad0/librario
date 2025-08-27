package main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;

import book.BookAvailable;
import book.BorrowedBook;
import book.Controller;
import user.Librerian;
import user.Student;


public class BookApp {

    public static void main(String[] args) {

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {

            Controller controller = new Controller();

            // Load books only once at startup
            HashMap<String, BookAvailable> availableBookMap = new HashMap<>();
            HashMap<String, BorrowedBook> borrowedBookMap = new HashMap<>();
            try (FileReader f = new FileReader("files/bookfile.txt")) {
                availableBookMap = controller.availableBookToMap(f);
            }
            try (FileReader f = new FileReader("files/borrowedbook.txt")) {
                borrowedBookMap = controller.BorrowedBookToMap(f);
            }

            // Load students only once
            HashMap<String, Student> studentMap = new HashMap<>();
            try (BufferedReader br = new BufferedReader(new FileReader("files/student.txt"))) {
                String line;
                int id;
                while ((line = br.readLine()) != null) {
                    String[] arr = line.split("\\s*,\\s*");
                    try {
                        id = Integer.parseInt(arr[0]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid student id, skipping entry.");
                        continue;
                    }
                    studentMap.put(arr[1].toLowerCase(), new Student(id, arr[1], arr[2]));
                }
            }

            HashMap<String, Librerian> librerianMap = new HashMap<>();
            try (BufferedReader br = new BufferedReader(new FileReader("files/librerian.txt"))) {
                String line;
                int id;
                while ((line = br.readLine()) != null) {
                    String[] arr = line.split("\\s*,\\s*");
                    try {
                        id = Integer.parseInt(arr[0]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid student id, skipping entry.");
                        continue;
                    }
                    librerianMap.put(arr[1].toLowerCase(), new Librerian(id, arr[1], arr[2]));
                }
            }

            // Main menu
            String line = "";
            while (true) {
                System.out.println("\nWelcome to Librerio");
                System.out.println("Are you:");
                System.out.println("1: Student");
                System.out.println("2: Librerian");
                System.out.println("q: Quit");

                line = bf.readLine().trim();
                if (line.equalsIgnoreCase("q")) {
                    System.out.println("Exiting application...");
                    break;
                }

                switch (line) {
                    case "1":
                        Student currentStudent = StudentLoop.login(bf, studentMap);
                        if (currentStudent != null) {
                            StudentLoop.printMenu(currentStudent, bf, availableBookMap, borrowedBookMap);
                        }
                        break;
                    case "2":
                        Librerian currentLibrerian = LibrerianLoop.login(bf, librerianMap);
                        if (currentLibrerian != null) {
                            LibrerianLoop.printMenu(currentLibrerian, bf, availableBookMap, borrowedBookMap);
                        }
                        break;
                    default:
                        System.out.println("Invalid option, try again.");
                }
            }

        } catch (IOException e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
