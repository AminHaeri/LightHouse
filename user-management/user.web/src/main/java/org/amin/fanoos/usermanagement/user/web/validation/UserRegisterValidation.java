package org.amin.fanoos.usermanagement.user.web.validation;

import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterValidation {

    public boolean validateRoleAuthorityToRegister(ERole currentUserRole, ERole newUserRole) {
        switch (newUserRole) {
            case ROLE_ADMIN -> {
                return currentUserRole == ERole.ROLE_SUPERADMIN;
            }
            case ROLE_USER -> {
                return (currentUserRole == ERole.ROLE_SUPERADMIN) || (currentUserRole == ERole.ROLE_ADMIN);
            }
            default -> {
                return false;
            }
        }
    }
}
