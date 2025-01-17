package com.example.ccsd.Payment;

import com.example.ccsd.Customer.Customer;
import com.example.ccsd.PaymentStatus.PaymentStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "payment")
public class Payment {

    @Id
    private String paymentId;
    private LocalDateTime paymentDate;
    private PaymentStatus paymentStatus;
    private Customer customer;
    private double amount;

    public void setPaymentId() {
        this.paymentId = UUID.randomUUID().toString();
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentId() {
        return this.paymentId;
    }

    public LocalDateTime getPaymentDate() {
        return this.paymentDate;
    }

    public PaymentStatus getPaymentStatus() {
        return this.paymentStatus;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public double getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", paymentDate=" + paymentDate +
                ", paymentStatus=" + paymentStatus +
                ", customer=" + customer +
                ", amount=" + amount +
                '}';
    }
}
