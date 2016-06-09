create table if not exists country
(
  id integer primary key autoincrement
, country text
);
create table if not exists genre
(
  id integer primary key autoincrement
, genre text
);
create table if not exists users
(
  id integer primary key autoincrement
, name text
, email text
, passwd text
);
create table if not exists movie
(
  id integer primary key autoincrement
, namerus text
, nameeng text
, yr      integer
, descr   text
, rating  numeric
, price   numeric
);
create table if not exists movie_genre
(
  id integer primary key autoincrement
, movieid integer
, genreid integer
, FOREIGN KEY(movieid) REFERENCES movie(id)
, FOREIGN KEY(genreid) REFERENCES genre(id)
);
create table if not exists movie_country
(
  id integer primary key autoincrement
, movieid integer
, countryid integer
, FOREIGN KEY(movieid) REFERENCES movie(id)
, FOREIGN KEY(countryid) REFERENCES country(id)
);
create table if not exists review
(
  id integer primary key autoincrement
, movieid integer
, userid integer
, review text
, FOREIGN KEY(movieid) REFERENCES movie(id)
, FOREIGN KEY(userid) REFERENCES users(id)
);