package com.example.ccsd.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody Customer customer) {

        try {

            System.out.println("Receiving " + customer);

            String name = customer.getName();
            String email = customer.getEmail();
            String phoneNo = customer.getPhoneNo();
            String cardNo = customer.getCardNo();
            Integer cardCvv = customer.getCardCvv();
            String cardExpiryDate = customer.getCardExpiryDate();
            LocalDate parsedCardExpiryDate = customer.getCardParsedExpiryDate();


            if (name==null || email==null || phoneNo==null || cardNo==null || cardCvv==null || cardExpiryDate==null) {
                throw new IllegalArgumentException("Missing required field for registration");
            }

            System.out.println("Verified " + customer);

            boolean authenticated = customerService.authenticateCard(cardNo, parsedCardExpiryDate, cardCvv);
            if (authenticated) {
                throw new IllegalArgumentException("Card authentication failed");
            }

            System.out.println("Authenticated card details for " + customer);

            Customer verifiedCustomer = new Customer();
            verifiedCustomer.setCustomerId();
            verifiedCustomer.setName(name);
            verifiedCustomer.setEmail(email);
            verifiedCustomer.setPhoneNo(phoneNo);
            verifiedCustomer.setCardNo(cardNo);
            verifiedCustomer.setCardCvv(cardCvv);
            verifiedCustomer.setCardExpiryDate(cardExpiryDate);

            System.out.println("Saving " + verifiedCustomer);

            Customer saveCustomer = customerService.save(verifiedCustomer);

            return ResponseEntity.ok(saveCustomer);

        } catch (IllegalArgumentException e) {
            System.out.println("Error occurred: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}
