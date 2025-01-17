package com.example.ccsd.PaymentStatus;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "paymentStatus")
public class PaymentStatus {

    @Id
    private String statusId;
    private String statusCode;
    private String statusDesc;

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getStatusId() {
        return this.statusId;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public String getStatusDesc() {
        return this.statusDesc;
    }

    @Override
    public String toString() {
        return "PaymentStatus{" +
                "statusId='" + statusId + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", statusDesc='" + statusDesc + '\'' +
                '}';
    }
}
