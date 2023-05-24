--liquibase formatted sql

--changeset blog:users
create table if not exists users
(
    id uuid primary key,
    first_name text not null,
    last_name text not null,
    picture_url text,
    role text not null,
    email text,
    password_hash text,
    created_at timestamptz not null
);

--changeset blog:auth_tokens
create table if not exists auth_tokens
(
    token text primary key,
    user_id uuid not null references users(id),
    expires_at timestamptz not null,
    created_at timestamptz not null
);
