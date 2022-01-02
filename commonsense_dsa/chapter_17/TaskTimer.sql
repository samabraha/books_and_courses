CREATE TABLE Tasks (_id INTEGER PRIMARY KEY NOT NULL, Name TEXT NOT NULL, Description TEXT, SortOrder INTEGER);
INSERT INTO Tasks (Name, Description) VALUES('TaskTimer', 'App creation');
INSERT INTO Tasks (Name, Description, SortOrder) VALUES('Android Java', 'Android Java course', 2);
INSERT INTO Tasks (Name, Description, SortOrder) VALUES('Android Kotlin', 'Android Kotlin course', 0);

SELECT * FROM Tasks;
SELECT * FROM Tasks ORDER BY Tasks.SortOrder DESC;