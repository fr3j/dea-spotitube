CREATE TABLE user_tokens (
                             token VARCHAR(255) PRIMARY KEY,
                             user VARCHAR(255) NOT NULL,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
