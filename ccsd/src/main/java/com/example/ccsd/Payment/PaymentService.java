package com.example.ccsd.Payment;

import com.example.ccsd.Customer.Customer;
import com.example.ccsd.Customer.CustomerService;
import com.example.ccsd.PaymentStatus.PaymentStatus;
import com.example.ccsd.PaymentStatus.PaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentStatusService paymentStatusService;

    public boolean authenticateCard(Customer customer, String cardNumber, Integer cvv) {
        if (!cardNumber.replace("-", "").equals(customer.getCardNo())) {
            return false;
        }
        if (!cvv.equals(customer.getCardCvv())) {
            return false;
        }

        return true;
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerService.getCustomerByEmail(email);
    }

    public Optional<PaymentStatus> getPaymentStatusByStatusCode(String statusCode) {
        return paymentStatusService.getPaymentStatusByStatusCode(statusCode);
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }
}
