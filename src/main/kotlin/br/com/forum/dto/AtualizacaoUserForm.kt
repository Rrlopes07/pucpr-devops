package br.com.forum.dto

import br.com.forum.model.Role
import jakarta.persistence.Enumerated
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.util.UUID

class AtualizacaoUserForm(
    @field:NotNull
    val id: UUID,

    @field:NotEmpty
    @field:Size(min = 10, max = 30)
    val email: String,

    @field:NotEmpty
    @field:Email
    val password: String,

    @field:NotEmpty
    @field:Enumerated
    val role: Role
)
