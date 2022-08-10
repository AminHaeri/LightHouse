package org.amin.fanoos.usermanagement.user.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserRegistrationController {

    @PostMapping
    @PreAuthorize("hasAuthority(T(org.amin.fanoos.usermanagement.user.application.domain.ERole).ROLE_SUPERADMIN)")
    public ResponseEntity<String> registerUser() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
