-- liquibase formatted sql

-- changeset mmayakina:1
CREATE INDEX student_name ON student(name);

-- changeset mmayakina:2
CREATE INDEX faculty_name_color ON faculty (name,color);