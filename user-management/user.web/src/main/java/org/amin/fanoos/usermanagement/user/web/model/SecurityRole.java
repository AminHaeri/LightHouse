package org.amin.fanoos.usermanagement.user.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SecurityRole implements GrantedAuthority {

    private ERole name;

    @Override
    public String getAuthority() {
        return getName().name();
    }
}
