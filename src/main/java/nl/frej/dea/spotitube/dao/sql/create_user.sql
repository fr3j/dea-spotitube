USE SpotiTube;

DROP TABLE IF EXISTS User;
CREATE TABLE User (
    id int,
    user varchar(48),
    password varchar(48),
    primary key (id)
);

INSERT INTO User (id, user, password) VALUES (3, 'frejmd5', MD5('abc123!@#'))