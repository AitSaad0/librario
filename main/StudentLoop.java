package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import book.BookAvailable;
import book.BorrowedBook;
import user.Student;

public class StudentLoop {

    static void printMenu(Student currentStudent, BufferedReader bf,
            HashMap<String, BookAvailable> availableBookMap,
            HashMap<String, BorrowedBook> borrowedBookMap) throws IOException {
        String input = "";
        String title = "";
        String fullName = currentStudent.getFullName();

        System.out.printf("\nwelcome %s! What do you want to do today? :)\n", fullName);
        while (true) {
            // Print menu
            System.out.println("1: Search for a book");
            System.out.println("2: Borrow a book");
            System.out.println("3: Return a book");
            System.out.println("q: Quit");

            input = bf.readLine().trim();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("Exiting menu...");
                break;
            }

            switch (input) {
                case "1":
                    System.out.print("Enter the title (q to quit): ");
                    title = bf.readLine().trim();
                    if (!title.equalsIgnoreCase("q")) {
                        currentStudent.bookInfo(title, availableBookMap, borrowedBookMap);
                        System.out.println("------------------------------------");
                    }
                    break;
                case "2":
                    System.out.print("Enter the title (q to quit): ");
                    title = bf.readLine().trim();
                    if (!title.equalsIgnoreCase("q")) {
                        currentStudent.borrowBook(title, availableBookMap, borrowedBookMap);
                        ;
                    }
                    System.out.println(
                            "you know had " + title + " please return it after you finish reading it, enjoy :)");
                    System.out.println("------------------------------------");
                    break;
                case "3":
                    System.out.print("Enter the title (q to quit): ");
                    title = bf.readLine().trim();
                    if (!title.equalsIgnoreCase("q")) {
                        currentStudent.returnBook(title, availableBookMap, borrowedBookMap);
                    }

                    System.out.println("you returned " + title + " thank you, please enjoy other book");
                    System.out.println("------------------------------------");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }

    
    static Student login(BufferedReader bf, HashMap<String, Student> studentMap) throws IOException {
        int attempts = 3;

        while (attempts > 0) {
            System.out.print("Username (q to quit): ");
            String username = bf.readLine().trim();
            if (username.equalsIgnoreCase("q"))
                return null;

            System.out.print("Password (q to quit): ");
            String password = bf.readLine().trim();
            if (password.equalsIgnoreCase("q"))
                return null;

            Student student = studentMap.get(username.toLowerCase());
            if (student != null && student.getPassword().equals(password)) {
                return student; // successful login
            } else {
                attempts--;
                System.out.println("Invalid credentials. Attempts left: " + attempts);
            }
        }

        System.out.println("No attempts left. Exiting login.");
        return null;
    }



}
