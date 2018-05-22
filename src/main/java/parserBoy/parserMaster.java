/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parserBoy;

import com.google.gson.Gson;
import com.mongodb.client.MongoDatabase;
import csvReader.csvHandler;
import entity.Author;
import entity.Book;
import entity.City;
import entity.Mentioned;
import entity.Wrote;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static main.NewMain.addToDb;
import mongoDBHandler.Mongo;

/**
 *
 * @author Andreas
 */
public class parserMaster {
    static Gson gson = new Gson();
    static Mongo db = new Mongo();
    static MongoDatabase c = db.getConnection().getDatabase("gutenberg");

    public List<Book> getBooks() throws IOException {
        csvHandler csv = new csvHandler();
        Map<String, City> cityList = csv.getCities();
        Map<String, Book> bookList = csv.getBooks();
        Map<String, Wrote> wroteList = csv.getWrote();
        List<Mentioned> mentionedList = csv.getMentioned();
        Map<String, Author> authorList = csv.getAuthors();

        for (Mentioned mention : mentionedList) {
            String bookID = mention.getBookID();
            String cityID = mention.getCityID().replaceFirst(" ", "");

            Book b = bookList.get(bookID);
            City c = cityList.get(cityID);

            b.addCity(c);
        }
        for (String key : wroteList.keySet()) {
            try {
                String authorID = wroteList.get(key).getAuthorID();
                String bookID = wroteList.get(key).getBookID();
                Author author = authorList.get(authorID);
                bookList.get(bookID).setAuthor(author);
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        List<Book> bookListNew = new ArrayList(bookList.values());
        return bookListNew;
    }
    
    public List<Book> getEverything() throws IOException {
        csvHandler csv = new csvHandler();
        Map<String, City> cityList = csv.getCities();
        Map<String, Book> bookList = csv.getBooks();
        Map<String, Wrote> wroteList = csv.getWrote();
        List<Mentioned> mentionedList = csv.getMentioned();
        Map<String, Author> authorList = csv.getAuthors();
        
        for (Mentioned mention : mentionedList) {
            String bookID = mention.getBookID();
            String cityID = mention.getCityID().replaceFirst(" ", "");

            Book b = bookList.get(bookID);
            City c = cityList.get(cityID);

            b.addCity(c);
        }
        for (String key : wroteList.keySet()) {
            try {
                String authorID = wroteList.get(key).getAuthorID();
                String bookID = wroteList.get(key).getBookID();
                Author author = authorList.get(authorID);
                bookList.get(bookID).setAuthor(author);
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        List<Book> bookListNew = new ArrayList(bookList.values());
        return bookListNew;
    }
}
