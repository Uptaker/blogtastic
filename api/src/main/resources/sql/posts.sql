--liquibase formatted sql

--changeset blog:posts
create table if not exists posts
(
    id uuid primary key,
    user_id uuid references users(id),
    title text,
    subheadline text not null,
    content text,
    date date,
    slug text not null,
    created_at timestamptz not null,
    updated_at timestamptz
);

--changeset blog:posts.slug.idx
create unique index posts_slug_idx on posts(slug)