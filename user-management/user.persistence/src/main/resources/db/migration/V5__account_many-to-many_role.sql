CREATE TABLE account_role
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT NOT NULL,
    role_id    BIGINT NOT NULL,
    CONSTRAINT FK_Account_TMP FOREIGN KEY (account_id) REFERENCES account (id),
    CONSTRAINT FK_Role FOREIGN KEY (role_id) REFERENCES role (id)
);