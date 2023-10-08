USE SpotiTube;

DROP TABLE IF EXISTS Track;
CREATE TABLE Track (
    id int,
    title varchar(48),
    performer varchar(48),
    duration int,
    album varchar(48),
    playcount int,
    publicationDate DATETIME,
    description varchar(96),
    offlineAvailable bit,
    primary key (id)
);

INSERT INTO Track
(id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable)
VALUES
    (1, 'Echoes of Time', 'John Doe', 320, 'Timeless', 2400, '2023-01-10', 'An instrumental track filled with ambient sounds.', 0),
    (2, 'Midnight Dance', 'Jane Smith', 290, 'Nightfall', 1500, '2022-10-02', 'A vibrant track perfect for night dances.', 0),
    (3, 'Sunny Days', 'The Clouds', 270, 'Sky High', 5000, '2021-06-21', 'Uplifting song for those bright sunny days.', 0),
    (4, 'Rainfall', 'Drip Drop', 300, 'Nature’s Melody', 1800, '2023-04-17', 'Relaxing sounds of rain and soft music.', 0),
    (5, 'Lost in Space', 'Galaxy Beats', 350, 'Stellar', 8000, '2022-12-05', 'Electronic vibes taking you through space.', 0);

