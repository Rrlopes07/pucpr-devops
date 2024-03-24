package br.com.forum.service

import br.com.forum.dto.AtualizacaoUserForm
import br.com.forum.dto.NovoUsuarioForm
import br.com.forum.dto.UsuarioView
import br.com.forum.exception.NotFoundException
import br.com.forum.extension.toDto
import br.com.forum.extension.toModel
import br.com.forum.model.User
import br.com.forum.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val repository: UserRepository,
    private val encoder: PasswordEncoder
    ){

    fun cadastrar(form: NovoUsuarioForm): UsuarioView {
        val usuario = form.toModel()
        usuario.apply {
            this.password = encoder.encode(this.password)
        }
        repository.save(usuario)

        return repository.findByEmail(usuario.email)?.toDto()
            ?: usuario.toDto()
    }

    fun encontrarPorEmail(email: String): User =
        repository.findByEmail(email)
            ?: throw NotFoundException("Usuário não localizado!")

    fun buscarPorId(idAutor: UUID): User =
        repository.findById(idAutor)
            .orElseThrow { NotFoundException("Usuário não localizado!") }

    fun listarTodos(): List<UsuarioView> =
        repository.findAll().map { it.toDto() }

    fun atualizar(form: AtualizacaoUserForm): UsuarioView =
        repository.findById(form.id)
            .orElseThrow { NotFoundException("Usuário não localizado!") }
            .also {
                it.email = form.email
                it.password = encoder.encode(form.password)
                it.role = form.role
            }.toDto()

    fun deletar(id: UUID) =
        repository.deleteById(id)

}
