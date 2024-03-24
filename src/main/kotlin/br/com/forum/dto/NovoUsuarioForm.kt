package br.com.forum.dto

import br.com.forum.model.Role
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class NovoUsuarioForm(
    @field:NotEmpty
    @field:Email
    val email: String,

    @field:NotEmpty
    @field:Size(min = 10, max = 30)
    val password: String,

    val role: Role
) {

}
