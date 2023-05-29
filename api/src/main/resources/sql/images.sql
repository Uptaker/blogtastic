--liquibase formatted sql

--changeset blog:images
create table if not exists images
(
    id uuid primary key,
    mime_type text not null,
    file bytea not null,
    created_at timestamptz not null
);