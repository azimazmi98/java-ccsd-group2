package com.example.ccsd.Customer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Document(collection = "customer")
public class Customer {

    @Id
    private String customerId;
    private String name;
    private String email;
    private String phoneNo;
    private String cardNo;
    private Integer cardCvv;
    private String cardExpiryDate;

    public void setCustomerId() {
        this.customerId = UUID.randomUUID().toString();
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo.replace("-", "");
    }

    public void setCardCvv(Integer cardCvv) {
        this.cardCvv = cardCvv;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public Integer getCardCvv() {
        return this.cardCvv;
    }

    public String getCardExpiryDate() {
        return this.cardExpiryDate;
    }

    public LocalDate getCardParsedExpiryDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");

        if (this.cardExpiryDate!=null) {
            return LocalDate.parse(this.cardExpiryDate + "-01", DateTimeFormatter.ofPattern("MM/yyyy-dd"));
        }
        // Append "-01" to the expiry date string to ensure it has a valid day component ("01")
        return null;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", cardCvv='" + cardCvv + '\'' +
                ", cardExpiryDate='" + cardExpiryDate + '\'' +
                '}';
    }
}
