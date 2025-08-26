package book;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class BookAvailable extends Book {
    public BookAvailable(String title, String author, int year, Category category) {
        super(title, author, year, category);
        availability = Availability.AVAILABLE;
    }

    public BookAvailable(String title, String author, int year) {
        super(title, author, year);
        availability = Availability.AVAILABLE;
    }

   
    

    Boolean ExistBook(String title, HashMap<String, BookAvailable> map) {
        if (map.get(title) != null) {
            return true;
        }
        return false;

    }

}
