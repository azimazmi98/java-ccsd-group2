//usersService.java
package com.example.ccsd.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class usersService {

    @Autowired
    private usersRepository usersRepository;


    public List<users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<users> getUserById(String userId) {
        return usersRepository.findById(userId);
    }

    public users addUser(users usersDetails){

        usersRepository.save(usersDetails);
        return usersDetails;
    }

    public users updateUser(String userId, users usersDetails){

        // Find the existing user by ID
        users existingUser = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Update the fields of the existing user with the new details
        if (usersDetails.getFirstName() != null) {
            existingUser.setFirstName(usersDetails.getFirstName());
        }

        if (usersDetails.getLastName() != null) {
            existingUser.setLastName(usersDetails.getLastName());
        }

        if (usersDetails.getEmail() != null) {
            existingUser.setEmail(usersDetails.getEmail());
        }

        if (usersDetails.getPhoneNumber() != null) {
            existingUser.setPhoneNumber(usersDetails.getPhoneNumber());
        }

        if (usersDetails.getAddress() != null) {
            existingUser.setAddress(usersDetails.getAddress());
        }

        if (usersDetails.getRole() != null) {
            existingUser.setRole(usersDetails.getRole());
        }

        if (usersDetails.getUsername() != null) {
            existingUser.setUsername(usersDetails.getUsername());
        }

        if (usersDetails.getDob() != null) {
            existingUser.setDob(usersDetails.getDob());
        }

        // Save the updated user back to the repository
        return usersRepository.save(existingUser);
    }

    public Optional<users> deleteUser(String userId){

        users existingUser = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        usersRepository.delete(existingUser);
        return Optional.empty();
    }

}
