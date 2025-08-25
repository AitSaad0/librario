package book;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class BookController {
    // private int year;
    // private Availability availability = Availability.UNKNOWN;
    // private Category category = Category.UNKNOWN;
    private Book book;

    // func1
    

    // func 2
    

    // func 3
    public void fromBorrowedToBook(HashMap<String, Book> borrowedMap, HashMap<String, Book> bookMap, String title,
            String fullName) {
        if (borrowedMap.containsKey(fullName)) {
            book = borrowedMap.get(title);
            borrowedMap.remove(fullName);
            book.setAvailability(Availability.AVAILABLE);
            bookMap.put(title, book);

        } else {
            if (bookMap.containsKey(title)) {
                book = bookMap.get(title);
                if (book.getAvailability() == Availability.AVAILABLE) {
                    System.out.println("The book isn't borrowed");
                } else {
                    book.setAvailability(Availability.AVAILABLE);
                    bookMap.put(title, book);
                }
            } else {
                System.out.println("Book doesn't exist");
                System.exit(1); // i will change that
            }
        }
    }

    // func 4 (should be modified after creating the student class)
    public void fromBookToBorrowed(HashMap<String, Book> borrowedMap, HashMap<String, Book> bookMap, String title) {
        if (bookMap.containsKey(title)) {
            book = bookMap.remove(title);
            book.setAvailability(Availability.BORREWED);
            borrowedMap.put(title, book);

        } else {
            if (borrowedMap.containsKey(title)) {
                book = borrowedMap.get(title);
                if (book.getAvailability() == Availability.BORREWED) {
                    System.out.println("The book already borrowed");
                } else {
                    book.setAvailability(Availability.BORREWED);
                    borrowedMap.put(title, book);
                }
            } else {
                System.out.println("Book doesn't exist");
                System.exit(1); // i will change that
            }
        }
    }

    // func 5
    Boolean ExistBook(String title, HashMap<String, Book> map) {
        if (map.get(title) != null) {
            return true;
        }
        return false;

    }
}
