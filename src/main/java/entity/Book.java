/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Cherry Rose Semeña
 */
public class Book {
    String bookID;
    String title;
    Author author;
    List<City> cities = new ArrayList();

    public Book(String bookID, String title) {
        this.bookID = bookID;
        this.title = title;
    }

    public Book(String bookID, String title, Author author) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
    }

    
    public String getId() {
        return bookID;
    }

    public void setId(String id) {
        this.bookID = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

  

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
    
    public void addCity(City city){
        cities.add(city);
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}
