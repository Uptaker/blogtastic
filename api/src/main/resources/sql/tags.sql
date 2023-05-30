--liquibase formatted sql

--changeset blog:tags
create table if not exists tags
(
    id text primary key,
    color text not null
);

--changeset blog:post_tags
create table if not exists post_tags
(
    id text primary key,
    post_id uuid not null references posts(id),
    tag_id text not null references tags(id)
);