CREATE TABLE AppUser
(
    id        VARCHAR(36) NOT NULL PRIMARY KEY,
    username  VARCHAR(36) NOT NULL,
    firstname VARCHAR(20),
    lastname  VARCHAR(50),
    age       VARCHAR(50),
    password  VARCHAR(20),
    email     VARCHAR(100)
);

CREATE TABLE Role
(
    id   VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(36) NOT NULL
);