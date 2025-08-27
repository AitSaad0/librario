package user;

import book.Availability;
import book.BookAvailable;
import book.BorrowedBook;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Student extends User {

    public Student(int id, String fullName, String password) {
        super(id, fullName, password);
    }


    public BookAvailable searchAvailbleBook(String title, HashMap<String, BookAvailable> availableBookMap)
            throws NullPointerException {
        if (availableBookMap.get(title) == null) {
            return null;
        }
        return availableBookMap.get(title);
    }

    public boolean borrowBook(String title, HashMap<String, BookAvailable> availableBookMap,
            HashMap<String, BorrowedBook> borrwedBookMap) {
        if (availableBookMap.containsKey(title)) {
            BookAvailable book = availableBookMap.remove(title);
            borrwedBookMap.put(title,
                    new BorrowedBook(title, book.getAuthor(), book.getYear(), book.getCategory(), this.fullName));
                    return true;

        } else {
            if (borrwedBookMap.containsKey(title)) {
                System.out.println("this book already borrowed check the list of available books");
                // here i want to return the list of availble books

            } else {
                System.out.println("Book doesn't exist");
                System.exit(1); // i will change that
            }
        }
        return false;
    }

    public boolean returnBook(String title, HashMap<String, BookAvailable> availableBookMap,
                       HashMap<String, BorrowedBook> borrowedBookMap) {
    if (borrowedBookMap.containsKey(title)) {
        BorrowedBook borrowedBook = borrowedBookMap.get(title);

        if (borrowedBook.getBorrower().equals(this.fullName)) {
            borrowedBookMap.remove(title);

            availableBookMap.put(title, new BookAvailable(
                    borrowedBook.getTitle(),
                    borrowedBook.getAuthor(),
                    borrowedBook.getYear(),
                    borrowedBook.getCategory()
            ));
            return true;
        } else {
            System.out.println("You cannot return this book because it was borrowed by another student.");
        }

    } else {
        System.out.println("You have not borrowed this book or it does not exist.");
    }
    return false;
}


    public boolean bookInfo(String title, HashMap<String, BookAvailable> availableBookMap,
            HashMap<String, BorrowedBook> borrwedBookMap) {
        if (availableBookMap.get(title) != null) {
            System.out.println(availableBookMap.get(title));
            return true;    
        } else if (borrwedBookMap.get(title) != null) {
            System.out.println(borrwedBookMap.get(title));
            return true;   
        } else {
            System.out.println("This book doesn't exist");
            return false;   
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
