create table if not exists notes
(
    id          serial primary key,
    title       text,
    description text
)