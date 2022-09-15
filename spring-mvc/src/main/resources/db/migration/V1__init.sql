create table persons
(
    id  bigint primary key,
    name varchar(36) not null,
    age bigint not null
);

insert into persons (id, name, age)
values (0, 'Rob', 23);