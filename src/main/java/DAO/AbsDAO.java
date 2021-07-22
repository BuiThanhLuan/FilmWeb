package DAO;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public abstract class AbsDAO {
    static MongoDatabase db;

    public static MongoDatabase getDB() {
        if (db == null) {
            ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .build();
            MongoClient mongoClient = MongoClients.create(settings);
            db = mongoClient.getDatabase("mflix");
        }
        return db;
    }
}
