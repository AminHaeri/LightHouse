package org.amin.fanoos.usermanagement.user.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "role")
public class RoleEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private Roles name;

    public enum Roles {
        ROLE_USER,
        ROLE_ADMIN,
        ROLE_SUPERADMIN
    }
}
