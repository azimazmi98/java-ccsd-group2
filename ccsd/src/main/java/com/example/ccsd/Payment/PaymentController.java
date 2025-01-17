package com.example.ccsd.Payment;

import com.example.ccsd.Customer.Customer;
import com.example.ccsd.PaymentStatus.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/submit")
    public ResponseEntity<Payment> submitPayment(@RequestBody PaymentRequest paymentRequest) {

        try {

            System.out.println("Received " + paymentRequest);

            String email = paymentRequest.getEmail();
            Double amount = paymentRequest.getAmount();
            String cardNumber = paymentRequest.getCardNumber();
            LocalDate expiryDate = paymentRequest.getParsedExpiryDate();
            Integer cvv = paymentRequest.getCvv();

            if (email==null || amount==null || cardNumber==null || expiryDate==null || cvv==null) {
                return ResponseEntity.badRequest().build();
            }

            Customer customer = paymentService.getCustomerByEmail(email)
                    .orElseThrow(() -> new NoSuchElementException("Customer with email " + email + " not found."));

            System.out.println("Verified customer " + customer);

            boolean authenticated = paymentService.authenticateCard(customer, cardNumber, cvv);
            if (!authenticated) {
                throw new IllegalArgumentException("Card authentication failed");
            }

            System.out.println("Authenticated " + paymentRequest);

            PaymentStatus paymentStatus = paymentService.getPaymentStatusByStatusCode("COMP")
                    .orElseThrow(() -> new NoSuchElementException("Status code not found."));

            System.out.println("Updating payment status with " + paymentStatus);

            Payment payment = new Payment();
            payment.setPaymentId();
            payment.setPaymentDate(LocalDateTime.now());
            payment.setPaymentStatus(paymentStatus);
            payment.setCustomer(customer);
            payment.setAmount(amount);

            System.out.println("Saving " + payment);

            Payment completePayment = paymentService.savePayment(payment);

            return ResponseEntity.ok(completePayment);

        } catch (NoSuchElementException e) {
            System.out.println("Error occurred: " + e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            System.out.println("Error occurred: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}

