package br.com.forum.extension

import br.com.forum.dto.TopicoView
import br.com.forum.model.Topico

fun Topico.toDto(): TopicoView =
    TopicoView(this.id, this.titulo, this.mensagem, this.status, this.dataCriacao)
