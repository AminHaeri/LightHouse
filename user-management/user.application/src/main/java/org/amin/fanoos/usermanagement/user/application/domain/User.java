package org.amin.fanoos.usermanagement.user.application.domain;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class User {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private Account account;
}
