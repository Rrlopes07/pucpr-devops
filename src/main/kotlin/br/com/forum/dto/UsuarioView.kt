package br.com.forum.dto

import br.com.forum.model.Role
import java.util.*

data class UsuarioView(
    var id: UUID? = null,
    val email: String,
    val role: Role
)
