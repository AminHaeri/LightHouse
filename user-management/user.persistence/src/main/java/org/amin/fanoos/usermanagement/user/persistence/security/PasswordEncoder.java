package org.amin.fanoos.usermanagement.user.persistence.security;

public interface PasswordEncoder {
    String hashedPassword(String password);
}
