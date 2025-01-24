CREATE TABLE member (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nick_name VARCHAR(255) NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        created_date_time TIMESTAMP NULL,
                        modified_date_time TIMESTAMP NULL
);