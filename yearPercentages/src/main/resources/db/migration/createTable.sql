create table if not exists users(
    id serial primary key,
    userName varchar(20) not null,
    isStarted boolean
); 