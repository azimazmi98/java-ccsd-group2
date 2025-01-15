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

        users addUser = null;
        return addUser;
    }

    public users updateUser(String userId, users usersDetails){

        users updateUser = null;
        return updateUser;
    }

    public Optional<users> deleteUser(String userId){

        return usersRepository.findById(userId);
    }

}
