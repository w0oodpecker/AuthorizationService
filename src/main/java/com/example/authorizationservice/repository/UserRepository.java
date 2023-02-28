package com.example.authorizationservice.repository;

import com.example.authorizationservice.exeptions.UnauthorizedUser;
import com.example.authorizationservice.model.Authorities;
import com.example.authorizationservice.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private static UserRepository instance;
    private HashMap<String, User> userMap;

    private UserRepository() {
        userMap = new HashMap<>();
    }

    public void addUser(String user, String password) {
        userMap.put(user, new User(user, password));
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        if (userMap.containsKey(user)) {
            if (userMap.get(user).getPassword() == password.hashCode()) {
                return userMap.get(user).getAuthoritiesList();
            }
        }
        return null;
    }

    public int addAuthority(String user, Authorities authority) {
        if (userMap.containsKey(user)) {
            userMap.get(user).addAuthorities(authority);
            return 1;
        }
        return -1;
    }

    public List<String> getUserList() {
        if (!userMap.isEmpty()) {
            return userMap.values().stream().map(user -> user.getUser()).collect(Collectors.toList());
        }
        return new ArrayList<String>();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }
}