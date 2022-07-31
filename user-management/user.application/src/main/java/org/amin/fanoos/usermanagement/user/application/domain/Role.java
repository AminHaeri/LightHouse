package org.amin.fanoos.usermanagement.user.application.domain;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Role {
    private UUID id;
    private ERole name;

    public enum ERole {
        ROLE_USER,
        ROLE_ADMIN,
        ROLE_SUPERADMIN
    }
}
