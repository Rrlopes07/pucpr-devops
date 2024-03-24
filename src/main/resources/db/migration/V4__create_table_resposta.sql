create table resposta(
    id BIGSERIAL PRIMARY KEY,
    mensagem varchar(300) not null,
    data_criacao timestamp not null DEFAULT CURRENT_TIMESTAMP,
    topico_id BIGSERIAL not null,
    autor_id UUID not null,
    solucao int not null,
    foreign key (topico_id) references topico(id),
    foreign key (autor_id) references usuario(id)
);
