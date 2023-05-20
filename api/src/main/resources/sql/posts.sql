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

--changeset blog:posts.slug
alter table posts add column slug text not null default 'REMOVE';

--changeset blog:posts.subheading
alter table posts add column subheadline text;