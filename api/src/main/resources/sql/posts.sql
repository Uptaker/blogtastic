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

--changeset blog:posts.userId
alter table posts add column user_id uuid not null references users(id);

--changeset blog:posts.slug.idx
create unique index posts_slug_idx on posts(slug)