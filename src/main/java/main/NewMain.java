/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.MongoClient;
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

import java.util.List;
import java.util.Map;
import java.util.Set;
import mongoDBHandler.Mongo;
import org.bson.Document;
import parserBoy.parserMaster;

/**
 *
 * @author Andreas
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    static Gson gson = new Gson();
    static Mongo db = new Mongo();
    static MongoDatabase c = db.getConnection().getDatabase("gutenberg");

    public static void main(String[] args) throws IOException {
        parserMaster parser = new parserMaster();
        List<Book> list = parser.getBooks();
        addToDb(list, c);
    }

    public static void addToDb(List<Book> list, MongoDatabase c) {
        for (Book o : list) {
            Document doc = Document.parse(gson.toJson(o));
            c.getCollection("books").insertOne(doc);
        }
    }

}
