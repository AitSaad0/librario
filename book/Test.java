package book;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Test {
    public static void main(String args[]) {
        BookAvailable book = new BookAvailable("test", "test", 0, Category.FANTASY);
        System.out.println(book.getAvailability());
    }
}
