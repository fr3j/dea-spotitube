USE SpotiTube;

DROP TABLE IF EXISTS Playlist;
CREATE TABLE Playlist (
    id int,
    name varchar(48),
    Owner varchar(48),
    primary key (id),
    foreign key (Owner) references User(user)
);