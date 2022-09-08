package org.amin.fanoos.usermanagement.user.web.util;

import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Optional;

public class SecurityUtils {

    public static ERole getCurrentUserRole() {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        Optional<SimpleGrantedAuthority> authority = authorities.stream().findFirst();
        String role = authority.orElseThrow().getAuthority();
        return ERole.valueOf(role);
    }
}
