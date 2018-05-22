/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvReader;

import entity.Author;
import entity.Book;
import entity.City;
import entity.Mentioned;
import entity.Wrote;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Andreas
 */
public class csvHandler {

    String cityPath = "C:/Users/Andreas/Desktop/csv/savage/dataimport/cleanCsv/cities_cleaned.csv";
    String authorPath = "C:/Users/Andreas/Desktop/csv/savage/dataimport/cleanCsv/authors_cleaned.csv";
    String booksPath = "C:/Users/Andreas/Desktop/csv/savage/dataimport/books.csv";
    String mentionedPath = "C:/Users/Andreas/Desktop/csv/savage/dataimport/mentioned.csv";
    String wrotePath = "C:/Users/Andreas/Desktop/csv/savage/dataimport/wrote.csv";

    public Map<String,City> getCities() throws IOException {
        Map<String,City> list = new HashMap();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(cityPath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
            for (CSVRecord csvRecord : csvParser) {
                if (csvRecord.get(2).equals("lat")) {

                } else {
                    try {// Accessing Values by Column Index
                        String cityID = csvRecord.get(0);
                        String name = csvRecord.get(1);
                        double lat = Double.valueOf(csvRecord.get(2));
                        double lon = Double.valueOf(csvRecord.get(3));
                        City city = new City(cityID, name, lat, lon);
                        list.put(cityID, city);

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }
            }
        }
        return list;
    }

    public Map<String,Author> getAuthors() throws IOException {
        Map<String,Author> list = new HashMap();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(authorPath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
            for (CSVRecord csvRecord : csvParser) {
                if (csvRecord.get(1).equals("fullName")) {
                } else {
                    try {// Accessing Values by Column Index
                        String authorID = csvRecord.get(0);
                        String fullName = csvRecord.get(1);
                        Author o = new Author(authorID, fullName);
                        list.put(authorID, o);

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }
            }
        }
        return list;
    }

    public Map<String,Book> getBooks() throws IOException {
        Map<String,Book> list = new HashMap();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(booksPath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
            for (CSVRecord csvRecord : csvParser) {
                if (csvRecord.get(0).equals("bookID")) {
                } else {
                    try {// Accessing Values by Column Index
                        String bookID = csvRecord.get(0);
                        String title = csvRecord.get(1).replaceFirst(" ", "");
                        Book o = new Book(bookID, title);
                        list.put(bookID, o);
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }
            }
        }
        return list;
    }

    public List<Mentioned> getMentioned() throws IOException {
        List<Mentioned> list = new ArrayList();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(mentionedPath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
            for (CSVRecord csvRecord : csvParser) {
                if (csvRecord.get(0).equals("bookID")) {
                } else {
                    try {// Accessing Values by Column Index
                        String bookID = csvRecord.get(0);
                        String cityID = csvRecord.get(1);
                        Mentioned o = new Mentioned(bookID, cityID);
                        list.add(o);
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }
            }
        }
        return list;
    }

    public Map<String,Wrote> getWrote() throws IOException {
        Map<String,Wrote> list = new HashMap();

        try (
                Reader reader = Files.newBufferedReader(Paths.get(wrotePath));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);) {
            for (CSVRecord csvRecord : csvParser) {
                if (csvRecord.get(0).equals("bookID")) {
                } else {
                    try {// Accessing Values by Column Index
                        String authorID = csvRecord.get(0);
                        String bookID = csvRecord.get(1);
                        Wrote o = new Wrote(authorID, bookID);
                        list.put(bookID, o);

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }
            }
        }
        return list;
    }

}
