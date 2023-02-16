CREATE TABLE human
(
    name TEXT PRIMARY KEY,
    age INTEGER,
    driverLicense BOOLEAN,
    car TEXT REFERENCES cars (model)
);

CREATE TABLE cars
(
    model TEXT PRIMARY KEY,
    brand TEXT,
    cost INTEGER
);

