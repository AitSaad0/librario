package book;

import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Book {
    protected String title;
    protected String author; 
    protected int year;
    protected Availability availability = Availability.UNKNOWN; 
    protected Category category = Category.UNKNOWN;

    
    public Book(String title, String author, int year, Availability availability, Category category){
        this.title = title; 
        this.author = author; 
        this.year = year; 
        this.availability = availability; 
        this.category = category; 
    }
    public Book(String title, String author, int year,  Category category){
        this.title = title; 
        this.author = author; 
        this.year = year; 
        this.availability = Availability.UNKNOWN; 
        this.category = category; 
    }
    public Book(String title, String author, int year,  Availability availability){
        this.title = title; 
        this.author = author; 
        this.year = year; 
        this.availability = availability; 
        this.category = Category.UNKNOWN; 
    }
    public Book(String title, String author, int year){
        this.title = title; 
        this.author = author; 
        this.year = year; 
        this.availability = Availability.UNKNOWN; 
        this.category = Category.UNKNOWN; 
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year = year;
    }

    public Availability getAvailability(){
        return availability;
    }
    public void setAvailability(Availability availability){
        this.availability = availability;
    }

    public Category getCategory(){
        return this.category;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    

    public String toString(){
        return "The Book name is " + title + ", writed by " + author + ", published in " + year + ", it's "
            + (availability == Availability.UNKNOWN ? "unavailable" : availability) + ", and category is : "
            + category;

    }


}



