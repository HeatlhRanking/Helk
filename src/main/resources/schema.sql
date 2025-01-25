DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS ranking;

CREATE TABLE member (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        email VARCHAR(50) NOT NULL,
                        password VARCHAR(50) NOT NULL,
                        nick_name VARCHAR(50) NOT NULL,
                        status VARCHAR(50) NOT NULL,
                        ranking_id BIGINT,
                        created_date_time DATETIME NOT NULL,
                        modified_date_time DATETIME NOT NULL,
                        FOREIGN KEY (ranking_id) REFERENCES ranking(id) ON DELETE CASCADE
);

CREATE TABLE ranking (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         ranking_value INT DEFAULT 0,
                         ranking_status VARCHAR(50) NOT NULL,
                         member_id BIGINT UNIQUE,
                         created_date_time DATETIME NOT NULL,
                         modified_date_time DATETIME NOT NULL,
                         FOREIGN KEY (member_id) REFERENCES member(id)
);

CREATE TABLE question (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          context VARCHAR(255),
                          member_id BIGINT,
                          likes INT,
                          created_date_time DATETIME NOT NULL,
                          modified_date_time DATETIME NOT NULL,
                          FOREIGN KEY (member_id) REFERENCES member(id)
);

CREATE TABLE answer (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        likes INT DEFAULT 0,
                        context VARCHAR(500) NOT NULL,
                        question_id BIGINT,
                        member_id BIGINT,
                        created_date_time DATETIME NOT NULL,
                        modified_date_time DATETIME NOT NULL,
                        FOREIGN KEY (question_id) REFERENCES question(id),
                        FOREIGN KEY (member_id) REFERENCES member(id)
);
