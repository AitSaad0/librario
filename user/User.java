package user;

import book.Book;

public abstract class User {
    protected String fullName; 
    private String password; 
    private int id; 
    protected Book book; 
    private static int counter = 0;

    protected User(String fullName, String password){
        this.fullName = fullName; 
        this.password = password;
        id = ++counter;
    }

}
