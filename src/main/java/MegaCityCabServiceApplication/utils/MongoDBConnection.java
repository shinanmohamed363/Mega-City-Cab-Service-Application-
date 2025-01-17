package MegaCityCabServiceApplication.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MongoDBConnection {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    static {
        try (InputStream input = MongoDBConnection.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find application.properties");
            }

            // Load properties and initialize the client
            Properties prop = new Properties();
            prop.load(input);
            String connectionUrl = prop.getProperty("mongodb.connection.url");
            String databaseName = prop.getProperty("mongodb.database.name");

            mongoClient = MongoClients.create(connectionUrl);
            database = mongoClient.getDatabase(databaseName);
            System.out.println("Connected to MongoDB successfully.");
        } catch (Exception e) {
            System.err.println("Failed to initialize MongoDB: " + e.getMessage());
            throw new RuntimeException(e); // Ensure the exception is logged and the app fails fast
        }
    }


    public static MongoDatabase getDatabase() {
        return database;
    }

    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("MongoDB connection closed.");
        }
    }
}
