create table topico(
    id BIGSERIAL PRIMARY KEY,
    titulo varchar(50) not null,
    mensagem varchar(300) not null,
    data_criacao timestamp not null DEFAULT CURRENT_TIMESTAMP,
    status varchar(20) not null,
    curso_id BIGSERIAL not null,
    autor_id UUID not null,
    foreign key(curso_id) references curso(id),
    foreign key(autor_id) references usuario(id)
);
