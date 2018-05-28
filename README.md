# parseMaster
A java program that load csv files and parse them to entities
and import objects to neo4j and mongodb databases.


# Source code details

```JAVA
csvHandler.class
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
```
The ```csvHandler.class``` read the csv file, and create objects from the records and putting them in a hashmap.




```JAVA
parserMaster.class
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
```
Here the books will get all objects that the book have relation to
* Author.class
* City.class

To figure out the relationships, we have to do a lookup on `Mentioned.csv, Wrote.csv` and for each tuple we do lookup on the hashmap bookList to get the book with bookID, thats why the hashmap is important here, because of the key-searching algorithm otherwise the loop will take forever.



```
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
```
Here will all books be imported to the mongodb database.


