package user;

import book.Availability;
import book.Book;
import java.util.HashMap;

public class Student extends User {

    Student(String fullName, String password) {
        super(fullName, password);
    }

    public Book searchBook(String title, HashMap<String, Book> bookMap) throws NullPointerException {
        book = bookMap.get(title);
        if (book == null) {
            return null;
        }
        return book;
    }

    public void borrowBook(String title, HashMap<String, Book> bookMap, HashMap<String, Book> borrwedBookMap) {
        if (bookMap.containsKey(title)) {
            book = bookMap.remove(title);
            book.setAvailability(Availability.BORREWED);
            borrwedBookMap.put(this.fullName, book);

        } else {
            if (borrwedBookMap.containsKey(this.fullName)) {
                while ((book = borrwedBookMap.get(this.fullName)) != null) {
                    if (book.getTitle() == title) {
                        if (book.getAvailability() != Availability.BORREWED) {
                            book.setAvailability(Availability.BORREWED);
                            borrwedBookMap.put(fullName, book);
                        }
                        System.out.println(title + " already borrowed");
                        
                    }
                }
                book = borrwedBookMap.get(this.fullName);

            } else {
                System.out.println("Book doesn't exist");
                System.exit(1); // i will change that
            }
        }
    }

}
