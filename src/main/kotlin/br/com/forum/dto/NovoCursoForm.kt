package br.com.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class NovoCursoForm(
    @field:NotEmpty
    @field:Size(min = 10, max = 100)
    val nome: String,

    @field:NotEmpty
    val categoria: String
)
