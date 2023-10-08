DROP DATABASE IF EXISTS SpotiTube;
CREATE DATABASE IF NOT EXISTS SpotiTube;

USE SpotiTube;

DROP TABLE IF EXISTS Playlist;
DROP TABLE IF EXISTS User;

-- Create the User table
DROP TABLE IF EXISTS User;
CREATE TABLE User (
                      id int,
                      user varchar(48),
                      password varchar(48),
                      primary key (id)
);

INSERT INTO User (id, user, password) VALUES (3, 'frejmd5', MD5('abc123!@#'));

DROP TABLE IF EXISTS Playlist;
CREATE TABLE Playlist (
                          id int,
                          name varchar(48),
                          Owner varchar(48),
                          primary key (id),
                          foreign key (Owner) references User(user)
);

DROP TABLE IF EXISTS user_tokens;
CREATE TABLE user_tokens (
                             token VARCHAR(255) PRIMARY KEY,
                             user VARCHAR(255) NOT NULL,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



INSERT INTO Playlist (id, name, Owner) VALUES (1, 'Electronic playlist 2020', 'frej');

SELECT * FROM User;
SELECT * FROM Playlist;

