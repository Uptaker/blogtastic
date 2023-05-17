--liquibase formatted sql

--changeset blog:posts
create table if not exists posts
(
    id uuid primary key,
    title text,
    content text,
    date date,
    created_at timestamptz not null,
    updated_at timestamptz
);