create table usuario (
    id uuid PRIMARY KEY,
    email varchar not null,
    password varchar not null,
    role varchar not null
);
