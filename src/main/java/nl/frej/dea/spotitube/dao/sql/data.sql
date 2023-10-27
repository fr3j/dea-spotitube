-- Drop the database if it exists
DROP DATABASE IF EXISTS SpotiTube;

-- Create the database
CREATE DATABASE IF NOT EXISTS SpotiTube;
USE SpotiTube;

-- Drop tables if they exist in the correct order due to foreign key constraints
DROP TABLE IF EXISTS Playlist_Track;
DROP TABLE IF EXISTS user_tokens;
DROP TABLE IF EXISTS Playlist;
DROP TABLE IF EXISTS User;

-- Create the User table
CREATE TABLE User (
                      id int PRIMARY KEY,
                      user varchar(48) UNIQUE,
                      password varchar(48)
);

-- Create the Playlist table
CREATE TABLE Playlist (
                          id int AUTO_INCREMENT PRIMARY KEY,
                          name varchar(48),
                          Owner varchar(48),
                          FOREIGN KEY (Owner) REFERENCES User(user)
);

-- Create user tokens table
CREATE TABLE user_tokens (
                             token VARCHAR(255) PRIMARY KEY,
                             user VARCHAR(255) NOT NULL,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Assuming Track table looks like this:
CREATE TABLE Track (
                       id int PRIMARY KEY,
                       title VARCHAR(255),
                       performer VARCHAR(255),
                       duration INT,
                       album VARCHAR(255),
                       playcount INT,
                       publicationDate DATE,
                       description TEXT,
                       offlineAvailable BOOLEAN
);

-- Create Playlist_Track junction table
CREATE TABLE Playlist_Track (
                                playlistId int,
                                trackId int,
                                FOREIGN KEY (playlistId) REFERENCES Playlist(id),
                                FOREIGN KEY (trackId) REFERENCES Track(id)
);

-- Sample Data Insertions
INSERT INTO User (id, user, password) VALUES (1, 'frejmd5', MD5('abc123!@#'));
INSERT INTO Playlist (name, Owner) VALUES ('Electronic playlist 2020', 'frejmd5'), ('Electronic playlist 2021', 'frejmd5');

-- Add sample tracks and playlist_tracks, if any
INSERT INTO Track
(id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable)
VALUES
    (1, 'Song for someone', 'The Frames', 350, 'The cost', NULL, NULL, NULL, 0),
    (2, 'The cost', 'The Frames', 423, NULL, 37, '2006-03-19', 'Title song from the Album The Cost', 1);

INSERT INTO Playlist_Track (playlistId, trackId) VALUES (
                                                            1, 1
                                                        ),
                                                        (1, 2);
-- Query sample data
SELECT * FROM User;
SELECT * FROM Playlist;
SELECT * FROM user_tokens;
SELECT * FROM Playlist_Track;
SELECT * FROM Track
