package org.amin.fanoos.usermanagement.user.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "role")
@EqualsAndHashCode(callSuper = true)
public class RoleEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "uid", nullable = false)
    private UUID uId;

    @Column(name = "name", nullable = false)
    private Roles name;

    @ManyToMany(mappedBy = "roleEntities")
    private Set<AccountEntity> accountEntities = new LinkedHashSet<>();

    public enum Roles {
        ROLE_USER,
        ROLE_ADMIN,
        ROLE_SUPERADMIN
    }
}
