package org.amin.fanoos.usermanagement.user.persistence.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public String hashedPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

        return encoder.encode(password);
    }
}
