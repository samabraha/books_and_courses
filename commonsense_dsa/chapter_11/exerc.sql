SELECT songs.track, songs.title, albums.name 
FROM songs JOIN albums ON songs.album = albums._id;

SELECT albums.name, songs.track, songs.title 
FROM songs JOIN albums ON songs.album = albums._id ORDER BY albums.name, songs.track;

SELECT artists.name, albums.name FROM artists 
INNER JOIN albums ON artists._id = albums.artist ORDER BY artists.name;

SELECT artists.name, albums.name, songs.track, songs.title 
FROM artists INNER JOIN albums INNER JOIN songs 
ON artists._id = albums.artist AND songs.album = albums._id 
ORDER BY artists.name, albums.name, songs.track;

SELECT artists.name, albums.name, songs.track, songs.title FROM songs
INNER JOIN albums ON songs.album  = albums._id
INNER JOIN artists ON albums.artist = artists._id
WHERE artists.name LIKE "jefferson%"
ORDER BY artists.name, songs.track;

CREATE VIEW artist_list AS
SELECT artists.name, albums.name, songs.track, songs.title FROM songs
INNER JOIN albums ON songs.album = albums._id
INNER JOIN artists ON albums.artist = artists._id
ORDER BY artists.name, albums.name, songs.track;

DELETE FROM songs WHERE track < 50;

SELECT albums.name, songs.track, songs.title FROM songs 
INNER JOIN albums ON songs.album = albums._id
WHERE albums.name = "Forbidden"
ORDER BY songs.track;

SELECT albums.name, songs.track, songs.title FROM songs
INNER JOIN albums ON songs.album = albums._id
INNER JOIN artists ON albums.artist = artists._id
WHERE artists.name = "Deep Purple"
ORDER BY albums.name, songs.track;

UPDATE artists SET name="One Kitten" WHERE artists.name = "Mehitabel";

SELECT COUNT(DISTINCT albums.name) FROM songs
INNER JOIN albums ON songs.album = albums._id
INNER JOIN artists ON albums.artist = artists._id
WHERE artists.name = "Aerosmith"
ORDER BY songs.title;