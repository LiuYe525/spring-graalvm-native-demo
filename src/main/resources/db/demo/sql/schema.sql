CREATE TABLE IF NOT EXISTS city
(
    id      INT PRIMARY KEY auto_increment,
    name    VARCHAR(128),
    state   VARCHAR(128),
    country VARCHAR(128)
);