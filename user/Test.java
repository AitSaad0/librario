package user;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import book.Controller;
import book.BookAvailable;
import book.BorrowedBook;

public class Test {
    public static void main(String args[]) {
        Controller controller = new Controller();
        HashMap<String, BookAvailable> bookAvailableMap = new HashMap<>();
        HashMap<String, BorrowedBook> borrowedBookMap = new HashMap<>();

        try (FileReader f = new FileReader("files/bookfile.txt")) {
            bookAvailableMap = controller.availableBookToMap(f);

            for (BookAvailable book : bookAvailableMap.values()) {
                System.out.println(book);
            }
            System.out.println("-----------------------");

        } catch (IOException e) {

        }
        try (FileReader f = new FileReader("files/borrowedbook.txt")) {
            borrowedBookMap = controller.BorrowedBookToMap(f);
            for (BorrowedBook book : borrowedBookMap.values()) {
                System.out.println(book);
            }
            System.out.println("-----------------------");

        } catch (IOException e) {

        }

        controller.fromBorrowedToAvailble(borrowedBookMap, bookAvailableMap, "dune");
        for (BookAvailable book : bookAvailableMap.values()) {
            System.out.println(book);
        }
        System.out.println("-----------------------");

        controller.fromAvailbleToBorrowed(borrowedBookMap, bookAvailableMap, "harry poter", "saad");
        for (BorrowedBook book : borrowedBookMap.values()) {
            System.out.println(book);
        }
        System.out.println("-----------------------");

    }
}
