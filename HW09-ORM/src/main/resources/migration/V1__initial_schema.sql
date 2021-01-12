create table account
(
    no varchar not null primary key,
    type varchar(50),
    rest float
);

create table client
(
    id bigserial not null primary key,
    name varchar(50),
    age integer
);

