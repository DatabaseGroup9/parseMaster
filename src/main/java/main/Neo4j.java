package main;

import csvReader.csvHandler;
import entity.Author;
import entity.Book;
import entity.City;
import java.util.List;
import java.util.Map;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;

import static org.neo4j.driver.v1.Values.parameters;
import parserBoy.parserMaster;

public class Neo4j implements AutoCloseable {

    private final Driver driver;
    private static String username = "neo4j";
    private static String password = "class";

    public Neo4j(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }

    public void addBookNode(Book book) {
        try (Session session = driver.session()) {
            session.run("CREATE (b:Book "
                    + "{bookID: $bookID,"
                    + "title: $bookTitle})",
                    parameters("bookID", book.getId(), "bookTitle", book.getTitle()));
        }
    }

    public void addAuthorNode(Author author) {
        try (Session session = driver.session()) {
            session.run("CREATE (a:Author "
                    + "{authorID: $authorID,"
                    + "fullName: $fullName})",
                    parameters("authorID", author.getAuthorID(), "fullName", author.getFullName()));
        }
    }

    public void addCityNode(City city) {
        try (Session session = driver.session()) {
            session.run("CREATE (c:City "
                    + "{cityID: $cityID,"
                    + "name: $cityName,"
                    + "lat: $lat,"
                    + "lon: $lon})",
                    parameters("cityID", city.getCityID(), "cityName", city.getName(), "lat", city.getLat(), "lon", city.getLon()));
        }
    }

    public void addRelationshipAuthored(Book book) {
        try (Session session = driver.session()) {
            session.run("MATCH (b:Book {bookID: $bookID}),"
                    + "(a:Author {authorID:$authorID})"
                    + "CREATE (a)-[:AUTHORED]->(b);",
                    parameters("bookID", book.getId(), "authorID", book.getAuthor().getAuthorID()));
        }
    }

    public void addRelationshipMentioned(Book book, City city) {
        try (Session session = driver.session()) {
            session.run("MATCH (b:Book {bookID: $bookID}),"
                    + "(c:City {cityID:$cityID})"
                    + "CREATE (b)-[:MENTIONS]->(c);",
                    parameters("bookID", book.getId(), "cityID", city.getCityID()));
        }
    }

    public void addIndex() {
        try (Session session = driver.session()) {
            session.run("CREATE INDEX ON :City(cityID)");
            session.run("CREATE INDEX ON :Book(bookID)");
            session.run("CREATE INDEX ON :Author(authorID)");
        }
    }

    public static void main(String... args) throws Exception {
        csvHandler csv = new csvHandler();
        parserMaster parser = new parserMaster();
        Map<String, City> cityList = csv.getCities();
        Map<String, Book> bookList = csv.getBooks();
        Map<String, Author> authorList = csv.getAuthors();
        List<Book> list = parser.getBooks();
        try (Neo4j greeter = new Neo4j("bolt://46.101.142.66:7687", username, password)) {

//            Map.Entry<String, City> entry = cityList.entrySet().iterator().next();
//            City value = entry.getValue();
//            greeter.addCityNode(value);
//            Map.Entry<String, Book> entry2 = bookList.entrySet().iterator().next();
//            Book value2 = entry2.getValue();
//            greeter.addBookNode(value2);
//            Map.Entry<String, Author> entry3 = authorList.entrySet().iterator().next();
//            Author value3 = entry3.getValue();
//            greeter.addAuthorNode(value3);
//            greeter.addIndex();
//            greeter.addRelationshipAuthored(list.get(0));
//            greeter.addRelationshipMentioned(list.get(0), list.get(0).getCities().get(0));
//            
            for (City city : cityList.values()) {
                greeter.addCityNode(city);
            }
            System.out.println("City finished");
            for (Author author : authorList.values()) {
                greeter.addAuthorNode(author);
            }
            System.out.println("Author finished");
            for (Book book : bookList.values()) {
                greeter.addBookNode(book);
            }
            System.out.println("book finished");
        }
    }
}
