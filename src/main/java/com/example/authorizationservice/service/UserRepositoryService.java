package com.example.authorizationservice.service;

import com.example.authorizationservice.exeptions.UnauthorizedUser;
import com.example.authorizationservice.model.Authorities;
import com.example.authorizationservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepositoryService {

    public UserRepository userRepository;

    public UserRepositoryService() {
        userRepository = UserRepository.getInstance();
    }

    public void addUser(String user, String password) {
        userRepository.addUser(user, password);
    }

    public void addAuthorities(String user, Authorities authority) {
        if (userRepository.addAuthority(user, authority) < 0) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
    }

    public List getUserList() {
        return userRepository.getUserList();
    }

}
