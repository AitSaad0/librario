package user;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import book.Book;
import book.BookController;

public class Test {
    public static void main(String args[]) {
        HashMap<String, Book> bookmap = new HashMap<>();
        HashMap<String, Book> borrowedmap = new HashMap<>();
        BookController bookController = new BookController();

        try (FileReader f = new FileReader("files/bookfile.txt")) {
            bookmap = bookController.BookToMap(f);
            System.out.println(bookmap.get("harry poter"));
        
        } catch (IOException e) {
            System.out.println("bookfile doesn t exist");
        }


        try (FileReader f = new FileReader("files/borrwedbook.txt")) {
            borrowedmap = bookController.BorrewedBookToMap(f, "saad");
            System.out.println(borrowedmap.get("saad"));
        } catch (NullPointerException e) {
            System.out.println("null");
        } catch (IOException e) {
            System.out.println("borrwedbook doesn t exist");
        }
    }
}
