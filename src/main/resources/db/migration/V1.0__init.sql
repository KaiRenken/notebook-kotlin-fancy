create table note
(
    id            uuid primary key,
    creation_date timestamp     not null,
    content       varchar(1024) not null
);