package com.example.ccsd.Payment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PaymentRequest {

    private String email;
    private Double amount;
    private String cardNumber;
    private String expiryDate;
    private Integer cvv;

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public LocalDate getParsedExpiryDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");

        if (this.expiryDate!=null) {
            return LocalDate.parse(this.expiryDate + "-01", DateTimeFormatter.ofPattern("MM/yyyy-dd"));
        }
        // Append "-01" to the expiry date string to ensure it has a valid day component ("01")
        return null;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "email='" + email + '\'' +
                ", amount=" + amount +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiryDate=" + expiryDate +
                ", cvv=" + cvv +
                '}';
    }

}
