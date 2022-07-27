CREATE TABLE account_role
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT NOT NULL,
    role_id    BIGINT NOT NULL,
    CONSTRAINT FK_AccountRole_Account FOREIGN KEY (account_id) REFERENCES account (id),
    CONSTRAINT FK_AccountRole_Role FOREIGN KEY (role_id) REFERENCES role (id)
);