package com.example.ccsd.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findAll().stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst();
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public boolean authenticateCard(String cardNumber, LocalDate expiryDate, Integer cvv) {
        // Check if card number matches the 12-digit format (ignoring hyphens)
        if (!cardNumber.replace("-", "").matches("\\d{12}")) {
            return false;
        }
        // Check if expiry date is before the current date
        LocalDate firstDayOfCurrentMonth = LocalDate.now().withDayOfMonth(1);
        if (expiryDate.isBefore(firstDayOfCurrentMonth)) {
            return false;
        }
        // Check if CVV is a 3-digit number
        if (cvv < 100 || cvv > 999) {
            return false;
        }

        return true;
    }

}
