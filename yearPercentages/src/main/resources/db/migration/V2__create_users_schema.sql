create table if not exists users
(
    chat_id    BIGINT primary key,
    user_name  varchar(20) not null,
    is_started boolean
);