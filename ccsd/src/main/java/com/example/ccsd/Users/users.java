//users.java
package com.example.ccsd.Users;



import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class users {

    private String password;
    private String email;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public String toString() {
        return "Email: " + email + "," +
                "Password: " + password;
    }

}
