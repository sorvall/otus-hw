create table homework_user
(
    id bigserial not null primary key,
    address_id bigint
);

create table address_data
(
    id bigserial not null primary key,
    address varchar
);

create table phone_data
(
    id bigserial not null primary key,
    number varchar,
    user_id bigint
);
