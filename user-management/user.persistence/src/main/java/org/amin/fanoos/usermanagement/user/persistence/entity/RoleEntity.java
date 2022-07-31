package org.amin.fanoos.usermanagement.user.persistence.entity;

import lombok.*;
import org.amin.fanoos.usermanagement.user.application.domain.Role;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "role")
public class RoleEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "uid", nullable = false, columnDefinition = "VARBINARY NOT NULL")
    private UUID uId;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR NOT NULL")
    @Enumerated(EnumType.STRING)
    private Role.ERole name;

    @ManyToMany(mappedBy = "roleEntities")
    private Set<AccountEntity> accountEntities = new LinkedHashSet<>();

    public RoleEntity(UUID uId, Role.ERole name) {
        super();
        this.uId = uId;
        this.name = name;
    }
}
