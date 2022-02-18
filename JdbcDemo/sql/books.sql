create table books (
    id      BIGSERIAL NOT NULL PRIMARY KEY,
    title   TEXT,
    price   DOUBLE PRECISION,
    qty     INTEGER
);

insert into books (title, price, qty) values ('Java', 248.51, 212);
insert into books (title, price, qty) values ('Am Ende eiens viel zu kurzen Tages (Death of a superhero)', 94.19, 1802);
insert into books (title, price, qty) values ('Casey Jones', 251.53, 822);
insert into books (title, price, qty) values ('Shaolin (Xin shao lin si)', 179.6, 1866);
insert into books (title, price, qty) values ('Harry in Your Pocket', 198.47, 937);
insert into books (title, price, qty) values ('Mad Love (Sappho)', 74.5, 56);
insert into books (title, price, qty) values ('Wave, The (Welle, Die)', 216.13, 1711);
insert into books (title, price, qty) values ('Clownhouse', 153.56, 612);
insert into books (title, price, qty) values ('Tortured', 151.66, 1661);
insert into books (title, price, qty) values ('Paranormal Activity 2', 229.63, 1619);
insert into books (title, price, qty) values ('Erendira', 264.55, 790);
