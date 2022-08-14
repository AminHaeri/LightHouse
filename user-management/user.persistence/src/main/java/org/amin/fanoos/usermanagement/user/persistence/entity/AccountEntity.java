package org.amin.fanoos.usermanagement.user.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "account")
public class AccountEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "uid", nullable = false, columnDefinition = "VARBINARY NOT NULL")
    private UUID uId;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(mappedBy = "accountEntity", orphanRemoval = true)
    private UserEntity userEntity;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<RoleEntity> roleEntities = new LinkedHashSet<>();

    public AccountEntity(UUID uId, String userName, String password,
                         Set<RoleEntity> roleEntities) {
        this.uId = uId;
        this.userName = userName;
        this.password = password;
        this.roleEntities = roleEntities;
    }
}
