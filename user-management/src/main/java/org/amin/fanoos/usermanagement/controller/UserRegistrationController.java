package org.amin.fanoos.usermanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserRegistrationController {

    @PostMapping
    public ResponseEntity<String> registerUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
