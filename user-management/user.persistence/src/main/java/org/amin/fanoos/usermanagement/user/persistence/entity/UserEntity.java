package org.amin.fanoos.usermanagement.user.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "user")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "uid", nullable = false, columnDefinition = "VARBINARY NOT NULL")
    private UUID uId;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;

    public UserEntity(UUID uId, String email, String firstName, String lastName, AccountEntity accountEntity) {
        this.uId = uId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountEntity = accountEntity;
    }
}
