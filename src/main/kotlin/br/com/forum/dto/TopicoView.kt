package br.com.forum.dto

import br.com.forum.model.StatusTopico
import java.io.Serializable
import java.time.LocalDateTime

data class TopicoView(
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime
) : Serializable