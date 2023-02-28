package com.example.authorizationservice.controller;

import com.example.authorizationservice.exeptions.InvalidCredentials;
import com.example.authorizationservice.exeptions.UnauthorizedUser;
import com.example.authorizationservice.model.Authorities;
import com.example.authorizationservice.service.AuthorizationService;
import com.example.authorizationservice.service.UserRepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AuthorizationController {
    private AuthorizationService service;
    private UserRepositoryService userRepositoryService;

    public AuthorizationController(AuthorizationService service, UserRepositoryService userRepositoryService) {
        this.service = service;
        this.userRepositoryService = userRepositoryService;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> invalidCredentialsHandler(InvalidCredentials exc) {
        return new ResponseEntity<>(exc.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> unauthorizedUserHandler(UnauthorizedUser exc) {
        return new ResponseEntity<>(exc.toString(), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/user")
    public void addUser(@RequestParam("user") String user, @RequestParam("password") String password) {
        userRepositoryService.addUser(user, password);
    }

    @GetMapping("/authority")
    public void addAuthorities(@RequestParam("user") String user, @RequestParam("authorities") Authorities authorities) {
        userRepositoryService.addAuthorities(user, authorities);
    }

    @GetMapping("/user/all")
    public List<String> getUserList() {
        return userRepositoryService.getUserList();
    }
}
