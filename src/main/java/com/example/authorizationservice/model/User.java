package com.example.authorizationservice.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String user;
    private int password; //храним хэш пароля
    private List<Authorities> AuthoritiesList;


    public User(String user, String password) {
        this.user = user;
        setPassword(password);
        AuthoritiesList = new ArrayList<>();
    }

    public String getUser() {
        return user;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.hashCode();
    }

    public List<Authorities> getAuthoritiesList() {
        return AuthoritiesList;
    }

    public void addAuthorities(Authorities authority) {
        this.AuthoritiesList.add(authority);
    }
}

