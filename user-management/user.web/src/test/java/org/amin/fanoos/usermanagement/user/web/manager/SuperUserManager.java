package org.amin.fanoos.usermanagement.user.web.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.amin.fanoos.usermanagement.user.application.domain.Account;
import org.amin.fanoos.usermanagement.user.application.domain.ERole;
import org.amin.fanoos.usermanagement.user.application.domain.Role;
import org.amin.fanoos.usermanagement.user.application.domain.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@Component
public class SuperUserManager {

    private final LinkedHashMap<String, ?> superUser;
    private final LinkedHashMap<String, String> superUserAccount;

    public SuperUserManager() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Resource resource = new ClassPathResource("SuperUser.json");

        this.superUser =  mapper.readValue(resource.getFile(), LinkedHashMap.class);
        this.superUserAccount = (LinkedHashMap<String, String>) this.superUser.get("account");
    }

    public User getSuperUser() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String hashedPassword = encoder.encode(this.superUserAccount.get("password"));

        Role role = new Role(UUID.randomUUID(), ERole.ROLE_SUPERADMIN);
        Account account = new Account(
                UUID.randomUUID(),
                this.superUserAccount.get("userName"),
                hashedPassword,
                List.of(role));

        return new User(
                UUID.randomUUID(),
                (String) this.superUser.get("email"),
                (String) this.superUser.get("firstName"),
                (String) this.superUser.get("lastName"),
                account);
    }

    public String getSuperUserRawPassword() {
        return this.superUserAccount.get("password");
    }
}
