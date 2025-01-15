//usersService.java
package com.example.ccsd.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class usersService {

    @Autowired
    private usersRepository usersRepository;

    public users getUserByEmail(String email) {
        return usersRepository.findAll().stream()
                .filter(users -> users.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
    
}
