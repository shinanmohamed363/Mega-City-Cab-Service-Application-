package MegaCityCabServiceApplication.services;

import MegaCityCabServiceApplication.models.User;
import MegaCityCabServiceApplication.utils.MongoDBConnection;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class AuthenticationService {
    private final MongoCollection<Document> userCollection;

    public AuthenticationService() {
        this.userCollection = MongoDBConnection.getDatabase().getCollection("users");
    }

    public User authenticate(String username, String password) {
        // Query MongoDB for the user
        Document query = new Document("username", username).append("password", password);
        Document userDoc = userCollection.find(query).first();

        if (userDoc != null) {
            // Convert the MongoDB document to a User object
            return new User(
                    userDoc.getString("username"),
                    userDoc.getString("password"),
                    userDoc.getString("type")
            );
        }

        return null; // User not found or invalid credentials
    }
}
