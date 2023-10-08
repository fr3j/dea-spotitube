USE SpotiTube;

DROP TABLE IF EXISTS Playlist_Track;
CREATE TABLE Playlist_Track (
                                playlistId int,
                                trackId int,
                                foreign key (playlistId) references Playlist(id),
                                foreign key (trackId) references Track(id)
);

INSERT INTO Playlist_Track (playlistId, trackId) VALUES (
                                   -1, 1
                                  ),
                                                     (-1, 2),
                                                        (-1, 3),
                                                        (-1, 4),
                                                        (-1, 5);