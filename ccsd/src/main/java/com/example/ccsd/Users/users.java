//users.java
package com.example.ccsd.Users;



import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class users {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String role;
    private String username;
    private String dob;

    private byte[] profPic;

    //Getter
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getDob() {
        return dob;
    }

    public byte[] getProfPic() {
        return profPic;
    }

    //Setter
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setRole(String role){
        this.role = role;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setDob(String dob){
        this.dob = dob;
    }

    public void setProfPic(byte[] imageBytes) {
        this.profPic = imageBytes;
    }
}
