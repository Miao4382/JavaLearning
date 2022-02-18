create table books (
    id      BIGSERIAL NOT NULL PRIMARY KEY,
    title   TEXT,
    price   TEXT,
    qty     INTEGER
);


-- insert some books to the books table
INSERT INTO books (title, price, qty) VALUES ('abc')