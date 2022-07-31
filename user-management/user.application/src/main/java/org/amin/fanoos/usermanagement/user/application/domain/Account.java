package org.amin.fanoos.usermanagement.user.application.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Account {
    private UUID id;
    private String userName;
    private String password;
    private List<Role> roles;
}
