package com.example.ccsd.PaymentStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PaymentStatusService {

    @Autowired
    private PaymentStatusRepository paymentStatusRepository;

    public Optional<PaymentStatus> getPaymentStatusByStatusCode(String statusCode) {
        return paymentStatusRepository.findAll().stream()
                .filter(paymentStatus -> paymentStatus.getStatusCode().equals(statusCode))
                .findFirst();
    }

}
