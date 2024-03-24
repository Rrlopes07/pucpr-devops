create table curso(
    id BIGSERIAL PRIMARY KEY,
    nome varchar not null,
    categoria varchar not null
);

insert into curso values (1, 'Kotlin', 'Programação');
