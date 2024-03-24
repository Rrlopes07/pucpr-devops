package br.com.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.util.UUID

data class NovoTopicoForm(
    @field:NotEmpty(message = "Título não pode ser em branco")
    @field:Size(min = 5, max = 100, message = "Título deve ter entre 5 e 100 caracteres")
    val titulo: String,

    @field:NotEmpty(message = "Mensagem não pode ser em branco")
    val mensagem: String,

    @field:NotNull(message = "Favor informar o id do curso")
    val idCurso: Long,

    @field:NotNull(message = "Favor informar o id do autor")
    val idAutor: UUID
)
