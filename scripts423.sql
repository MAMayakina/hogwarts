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



SELECT students.name, students.age, students.faculty, faculties.color
FROM students
         INNER JOIN faculties ON students.faculty = faculties.name;



SELECT students.name, students.age, students.faculty, faculties.color
FROM students
         INNER JOIN faculties ON students.faculty = faculties.name
WHERE students.avatar = 'true';
