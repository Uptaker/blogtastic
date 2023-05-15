-- For compatibility with PostgreSQL
create domain if not exists timestamptz as timestamp;

create table if not exists posts
(
    id varchar(60) default random_uuid() primary key,
    title text,
    content text,
    date date,
    created_at timestamptz not null,
    updated_at timestamptz
);