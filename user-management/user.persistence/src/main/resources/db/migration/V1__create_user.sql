CREATE TABLE user
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    uid        VARBINARY(255) NOT NULL UNIQUE,
    email      VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP
);