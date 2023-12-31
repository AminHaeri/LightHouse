CREATE TABLE account
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    uid        VARBINARY(255) NOT NULL UNIQUE,
    username   VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP
);