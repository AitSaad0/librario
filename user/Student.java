package user;

import book.Availability;
import book.BookAvailable;
import book.BorrowedBook;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Student extends User {

    Student(String fullName, String password, int id) {
        super(fullName, password, id);
    }

    public BookAvailable searchAvailbleBook(String title, HashMap<String, BookAvailable> availableBookMap)
            throws NullPointerException {
        if (availableBookMap.get(title) == null) {
            return null;
        }
        return availableBookMap.get(title);
    }

    public void borrowBook(String title, HashMap<String, BookAvailable> availableBookMap,
            HashMap<String, BorrowedBook> borrwedBookMap) {
        if (availableBookMap.containsKey(title)) {
            BookAvailable book = availableBookMap.remove(title);
            borrwedBookMap.put(title,
                    new BorrowedBook(title, book.getAuthor(), book.getYear(), book.getCategory(), this.fullName));

        } else {
            if (borrwedBookMap.containsKey(title)) {
                System.out.println("this book already borrowed check the list of available books");
                // here i want to return the list of availble books

            } else {
                System.out.println("Book doesn't exist");
                System.exit(1); // i will change that
            }
        }
    }

    public void bookInfo(String title, HashMap<String, BookAvailable> availableBookMap,
            HashMap<String, BorrowedBook> borrwedBookMap) {
        if (availableBookMap.get(title) != null) {
            System.out.println(availableBookMap.get(title));
        } else if (borrwedBookMap.get(title) != null) {
            System.out.println(borrwedBookMap.get(title));
        } else {
            System.out.println("This book doesn't exist");
            // here i want to return the list of availble books
        }
    }

    public void AvailbleBook(HashMap<String, BookAvailable> availableBookMap) {
        if (availableBookMap.isEmpty()) {
            System.out.println("There is no available book tr later");

        } else {

            System.out.println("The Available books are");
            for (Map.Entry<String, BookAvailable> entry : availableBookMap.entrySet()) {
                System.out.print(entry.getValue());

            }
        }
    }
}
