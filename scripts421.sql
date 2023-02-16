CREATE TABLE students
(
    name    TEXT UNIQUE NOT NULL PRIMARY KEY,
    age     INTEGER CHECK (age > 0) DEFAULT 20,
    faculty TEXT REFERENCES faculties (name),
    avatar  BOOLEAN

);

CREATE TABLE faculties
(
    name  TEXT UNIQUE NOT NULL PRIMARY KEY,
    color TEXT UNIQUE NOT NULL
);