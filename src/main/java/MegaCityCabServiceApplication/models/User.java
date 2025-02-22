package MegaCityCabServiceApplication.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String username;
    private String password;
    private String type;

    // Default constructor (required by Jackson for deserialization)
    public User() {}

    public User(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
