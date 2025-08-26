package user;

import book.Book;

public abstract class User {
    protected String fullName; 
    private String password; 
    private int id; 
    protected Book book; 

    protected User(String fullName, String password, int id){
        this.fullName = fullName; 
        this.password = password;
        this.id = id;
    }

}
