package user;

import book.Book;

public abstract class User {
    protected String fullName; 
    protected String password; 
    private int id;
    // private Type type; 
    protected Book book; 

    protected User(int id, String fullName, String password /*, Type type*/){
        this.id = id;
        this.fullName = fullName; 
        this.password = password;
        // this.type = type;
    }

    public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }


    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    
    public String toString(){
        return "my name is " + fullName;
    }

}
