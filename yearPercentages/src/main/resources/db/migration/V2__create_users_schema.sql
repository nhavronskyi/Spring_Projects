create table if not exists users
(
    chat_id    serial primary key,
    user_name  varchar(20) not null,
    is_started boolean
);