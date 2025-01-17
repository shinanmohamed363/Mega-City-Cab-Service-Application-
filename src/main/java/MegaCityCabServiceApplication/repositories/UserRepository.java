package MegaCityCabServiceApplication.repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import MegaCityCabServiceApplication.models.User;
import MegaCityCabServiceApplication.utils.MongoDBConnection;

public class UserRepository {
    private final MongoDatabase database;
    private final MongoCollection<Document> userCollection;

    public UserRepository() {
        database = MongoDBConnection.getDatabase();
        userCollection = database.getCollection("users"); // MongoDB collection for users
    }

    public void addUser(User user) {
        Document userDocument = new Document("username", user.getUsername())
                .append("password", user.getPassword())  // Password should be hashed in a real app
                .append("type", user.getType());

        userCollection.insertOne(userDocument);
        System.out.println("User registered successfully.");
    }
}
