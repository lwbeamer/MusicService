CREATE TABLE Country
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL UNIQUE
);

CREATE TABLE Subscription
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(32)  NOT NULL UNIQUE,
    description VARCHAR(256) NOT NULL UNIQUE,
    price       INTEGER      NOT NULL
);

CREATE TABLE Roles
(
    id   SERIAL PRIMARY KEY,
    name varchar(20)
);

CREATE TABLE Uzer
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(32)  NOT NULL,
    surname    VARCHAR(32)  NOT NULL,
    login      VARCHAR(32)  NOT NULL UNIQUE,
    password   VARCHAR(128) NOT NULL,
    role_id    INTEGER REFERENCES roles ON DELETE CASCADE ON UPDATE CASCADE,
    id_sub     INTEGER REFERENCES subscription ON DELETE CASCADE ON UPDATE CASCADE,
    sub_start  timestamp with time zone DEFAULT current_timestamp,
    id_country INTEGER REFERENCES Country ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Admin
(
    id      SERIAL PRIMARY KEY,
    id_uzer INTEGER REFERENCES Uzer ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Organisation
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(32)  NOT NULL UNIQUE,
    description VARCHAR(256) NOT NULL UNIQUE,
    id_country  INTEGER REFERENCES Country ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Artist
(
    id          SERIAL PRIMARY KEY,
    id_uzer     INTEGER REFERENCES Uzer ON DELETE CASCADE ON UPDATE CASCADE,
    name        VARCHAR(32) NOT NULL UNIQUE,
    description VARCHAR(256),
    id_org      INTEGER REFERENCES Organisation ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Album
(
    id          SERIAL PRIMARY KEY,
    type        VARCHAR(32) NOT NULL DEFAULT 'Отсутствуют треки',
    name        VARCHAR(32) NOT NULL,
    description VARCHAR(256),
    link       VARCHAR(256) NOT NULL UNIQUE
);

-- CREATE TABLE Artists_Albums
-- (
--     id_artist INTEGER REFERENCES Artist ON DELETE CASCADE ON UPDATE CASCADE,
--     id_album  INTEGER REFERENCES Album ON DELETE CASCADE ON UPDATE CASCADE,
--     PRIMARY KEY (id_artist, id_album)
-- );

CREATE TABLE Genre
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL UNIQUE
);

CREATE TABLE Song
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(32)              NOT NULL,
    duration    INTEGER                  NOT NULL CHECK (duration > 5),
    id_album    INTEGER REFERENCES Album ON DELETE CASCADE ON UPDATE CASCADE,
    id_admin    INTEGER REFERENCES Admin ON DELETE CASCADE ON UPDATE CASCADE,
    id_genre    INTEGER REFERENCES Genre ON DELETE CASCADE ON UPDATE CASCADE,
    last_change timestamp with time zone NOT NULL DEFAULT '1999-10-19 10:23:54+02',
    link       VARCHAR(256) NOT NULL UNIQUE
);

