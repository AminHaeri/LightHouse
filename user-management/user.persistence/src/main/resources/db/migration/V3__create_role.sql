CREATE TABLE role
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    uid        VARCHAR(255) NOT NULL UNIQUE,
    name       VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP
);