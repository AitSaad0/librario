package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import book.BookAvailable;
import book.BorrowedBook;
import book.Category;
import user.Librerian;

public class LibrerianLoop {

    static class BookInput {
        String title, author;
        int year;
        Category category;

        BookInput(String title, String author, int year, Category category) {
            this.title = title;
            this.author = author;
            this.year = year;
            this.category = category;
        }
    }

    private static BookInput readBookInput(BufferedReader bf) throws IOException {
        System.out.print("Enter the title (q to quit): ");
        String title = bf.readLine().trim().toLowerCase();
        if (title.equalsIgnoreCase("q"))
            return null;

        System.out.print("Enter the author (q to quit): ");
        String author = bf.readLine().trim().toLowerCase();
        if (author.equalsIgnoreCase("q"))
            return null;

        System.out.print("Enter the year (q to quit): ");
        String yearInput = bf.readLine().trim().toLowerCase();
        if (yearInput.equalsIgnoreCase("q"))
            return null;

        int year;
        try {
            year = Integer.parseInt(yearInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid year, quitting...");
            return null;
        }

        System.out.print(
                "What is the category (q to quit):\n1: SCIFY\n2: FANTASY\n3: SCIENCE\n4: STORY\n");
        String categoryInput = bf.readLine().trim().toLowerCase();
        if (categoryInput.equals("q"))
            return null;

        Category category;
        switch (categoryInput) {
            case "scify", "1" -> category = Category.SCIFY;
            case "fantasy", "2" -> category = Category.FANTASY;
            case "science", "3" -> category = Category.SCIENCE;
            case "story", "4" -> category = Category.STORY;
            default -> category = Category.UNKNOWN;
        }

        return new BookInput(title, author, year, category);
    }

    static void printMenu(Librerian currentLibrerian, BufferedReader bf,
            HashMap<String, BookAvailable> availableBookMap,
            HashMap<String, BorrowedBook> borrowedBookMap) throws IOException {

        String input = "";
        boolean done = false;
        System.out.printf("\nWelcome %s! What do you want to do today? :)\n",
                currentLibrerian.getFullName());

        while (true) {
            System.out.println("1: Add an available book");
            System.out.println("2: Add a borrowed book");
            System.out.println("3: Remove an available book");
            System.out.println("4: Remove a borrowed book");
            System.out.println("q: Quit");

            input = bf.readLine().trim().toLowerCase();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("Exiting menu...");
                WriterIntoLogs.printLog("Librerian", currentLibrerian.getFullName(), "logout");
                break;
            }

            switch (input) {
                case "1" -> {
                    BookInput book = readBookInput(bf);
                    if (book == null)
                        break;

                    done = currentLibrerian.addBook(book.title, book.author, book.year, book.category,
                            availableBookMap);
                    if (done) {
                        System.out.println(availableBookMap.get(book.title));
                        WriterIntoLogs.printLog("Librerian", currentLibrerian.getFullName(),
                                "added an available book :",
                                book.title);
                    } else {
                        WriterIntoLogs.printLog("Librerian", currentLibrerian.getFullName(),
                                "try to add an existing available book :",
                                book.title);
                    }
                    System.out.println("------------------------------------");

                }

                case "2" -> {
                    BookInput book = readBookInput(bf);
                    if (book == null)
                        break;

                    System.out.print("Enter the borrower (q to quit): ");
                    String borrower = bf.readLine().trim();
                    if (borrower.equalsIgnoreCase("q"))
                        break;

                    done = currentLibrerian.addBook(book.title, book.author, book.year, book.category, borrower,
                            borrowedBookMap);
                    if (done) {
                        System.out.println(borrowedBookMap.get(book.title));
                        WriterIntoLogs.printLog("Librerian", currentLibrerian.getFullName(), "added a borrowed book :",
                                book.title);
                    } else {
                        WriterIntoLogs.printLog("Librerian", currentLibrerian.getFullName(),
                                "try to add an existing borrowed book :",
                                book.title);
                    }
                    System.out.println("------------------------------------");
                }

                case "3" -> {
                    System.out.print("Enter the title (q to quit): ");
                    String title = bf.readLine().trim().toLowerCase();
                    if (title.equalsIgnoreCase("q"))
                        break;

                    BookAvailable removedBook = currentLibrerian.removeBook(title, availableBookMap);
                    if (removedBook != null) {
                        System.out.println("You removed " + removedBook.getTitle() + " from available books");
                        WriterIntoLogs.printLog("Librerian", currentLibrerian.getFullName(),
                                "remove an available book :", title);
                    } else {
                        System.out.println("Couldn't find this book");
                        WriterIntoLogs.printLog("Librerian", currentLibrerian.getFullName(),
                                "try to remove a none existing available book :", title);
                    }
                    System.out.println("------------------------------------");
                }

                case "4" -> {
                    System.out.print("Enter the title (q to quit): ");
                    String title = bf.readLine().trim().toLowerCase();
                    if (title.equalsIgnoreCase("q"))
                        break;

                    System.out.print("Enter the borrower (q to quit): ");
                    String borrower = bf.readLine().trim().toLowerCase();
                    if (borrower.equalsIgnoreCase("q"))
                        break;

                    BorrowedBook removedBook = currentLibrerian.removeBook(title, borrower, borrowedBookMap);
                    if (removedBook != null) {
                        System.out.println("You removed " + removedBook.getTitle() + " from borrowed books");
                        WriterIntoLogs.printLog("Librerian", currentLibrerian.getFullName(), "had remove a borrowed book :",
                                title);
                    } else {
                        System.out.println("Couldn't find this book");
                        WriterIntoLogs.printLog("Librerian", currentLibrerian.getFullName(), "try to remove a none existing borrowed book :",
                                title);
                    }
                    System.out.println("------------------------------------");
                }

                default -> System.out.println("Invalid option, try again.");
            }
        }
    }

    static Librerian login(BufferedReader bf, HashMap<String, Librerian> librerianMap) throws IOException {
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

            Librerian librerian = librerianMap.get(username.toLowerCase());
            if (librerian != null && librerian.getPassword().equals(password)) {
                WriterIntoLogs.printLog("Librerian", username, "login");
                return librerian; // successful login
            } else {
                attempts--;
                System.out.println("Invalid credentials. Attempts left: " + attempts);
            }
        }

        System.out.println("No attempts left. Exiting login.");
        return null;
    }
}
