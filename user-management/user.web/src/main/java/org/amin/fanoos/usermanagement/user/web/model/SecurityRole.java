package org.amin.fanoos.usermanagement.user.web.model;

import lombok.Builder;
import lombok.Data;
import org.amin.fanoos.usermanagement.user.application.domain.Role;
import org.springframework.security.core.GrantedAuthority;

@Builder
@Data
public class SecurityRole implements GrantedAuthority {

    private Role.ERole name;

    @Override
    public String getAuthority() {
        return getName().name();
    }
}
