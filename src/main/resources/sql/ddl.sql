CREATE TABLE member (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(64) NOT NULL,
    name VARCHAR(32) NOT NULL,
    password VARCHAR(64) NOT NULL,
    phone VARCHAR(13) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY idx_unique_email (email)
);