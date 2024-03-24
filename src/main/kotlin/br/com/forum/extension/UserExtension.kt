package br.com.forum.extension

import br.com.forum.dto.NovoUsuarioForm
import br.com.forum.dto.UsuarioView
import br.com.forum.model.User

fun NovoUsuarioForm.toModel(): User =
    User(email = this.email, password = this.password, role = this.role)

fun User.toDto(): UsuarioView =
    UsuarioView(id = this.id , email = this.email, role = this.role)
