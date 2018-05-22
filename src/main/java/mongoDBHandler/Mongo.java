/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongoDBHandler;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;

/**
 *
 * @author Andreas
 */
public class Mongo {

    private MongoClient mongoClient = null;
    private String URI = "mongodb://myUserAdmin:abc123@206.189.21.241:27017";

    public MongoClient getConnection() {
        try {
            this.mongoClient = new MongoClient(new MongoClientURI(URI));

        } catch (Exception e) {
            System.out.println("ERROR IN MONGODB CONNECTION" + e.toString());
        }
        return mongoClient;
    }
}
