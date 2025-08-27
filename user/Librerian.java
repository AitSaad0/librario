package user;

import java.util.HashMap;

import book.Availability;
import book.Category;
import book.BookAvailable;
import book.BorrowedBook;

public class Librerian extends User {
    public Librerian(int id, String fullName, String password) {
        super(id, fullName, password);
    }

    public boolean addBook(String title,
            String author,
            int year,
            Category category, 
            HashMap<String, BookAvailable> map) {
                if(map.get(title) == null){
                    map.put(title, new BookAvailable(title, author, year, category));
                    return true;
                }else{
                    System.out.println("this book already exist");
                    return false;
                }
    }
    
    public boolean addBook(String title,
            String author,
            int year,
            Category category, 
            String borrower,
            HashMap<String, BorrowedBook> map) {
                if(map.get(title) == null){
                    map.put(title, new BorrowedBook(title, author, year, category, borrower));
                    return true;
                }else{
                    System.out.println("this book already exist");
                    return false;
                }
    }

    public BookAvailable removeBook(String title,  HashMap<String, BookAvailable> map){
        if(map.get(title) == null){
            return null;
        }
        return map.remove(title);
    }

    public BorrowedBook removeBook(String title, String borrower, HashMap<String, BorrowedBook> map){
        if(map.get(title) == null){
            return null;
        }
        return map.remove(title);
    }
}
