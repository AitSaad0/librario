package book;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class BorrowedBook extends Book {
    private String borrower;

    public BorrowedBook(String title, String author, int year, Category category, String borrower) {
        super(title, author, year, category);
        availability = Availability.BORREWED;
        this.borrower = borrower;
    }

    public BorrowedBook(String title, String author, int year, String borrower) {
        super(title, author, year);
        availability = Availability.BORREWED;
        this.borrower = borrower;
    }

    public HashMap<String, Book> BorrowedBookToMap(FileReader filename) {
        HashMap<String, Book> map = new HashMap<>();
        int year = 0;
        Category category = Category.UNKNOWN;

        try (BufferedReader br = new BufferedReader(filename)) {
            String str;
            while ((str = br.readLine()) != null) {
                String[] arr = str.split("\\s*,\\s*");
                switch (arr[4]) {
                    case "SCIFY":
                        category = Category.SCIFY;
                        break;
                    case "FANTASY":
                        category = Category.FANTASY;
                        break;
                    case "SCIENCE":
                        category = Category.SCIENCE;
                        break;
                    case "STORY":
                        category = Category.STORY;
                        break;
                    default:
                        category = Category.UNKNOWN;
                }
                try {
                    year = Integer.parseInt(arr[2]);
                } catch (NumberFormatException e) {
                    System.out.println("enter a valid year");
                    year = 0; // i want to call the menu object or try somthing else you should design a
                              // higher-level mechanism (like throwing a custom exception or calling a
                              // callback
                }
                map.put(arr[0].toLowerCase(), new BorrowedBook(arr[0], arr[1], year, category, arr[5]));
            }

        } catch (FileNotFoundException e) {
            System.out.println("❌ Couldn't open the file: " + filename);
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You try to access a value out of bound, see the borrowed book file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}
