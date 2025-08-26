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
        availability = Availability.BORROWED;
        this.borrower = borrower;
    }

    public BorrowedBook(String title, String author, int year, String borrower) {
        super(title, author, year);
        availability = Availability.BORROWED;
        this.borrower = borrower;
    }

    public String toString() {
        return "The Book name is " + title + ", writed by " + author + ", published in " + year + ", it's "
                + (availability == Availability.UNKNOWN ? "unavailable" : availability) + ", and category is : "
                + category + ", borrowed by " + borrower;

    }
}
