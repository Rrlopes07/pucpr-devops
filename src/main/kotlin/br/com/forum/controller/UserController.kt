package br.com.forum.controller

import br.com.forum.dto.AtualizacaoUserForm
import br.com.forum.dto.NovoUsuarioForm
import br.com.forum.dto.UsuarioView
import br.com.forum.model.User
import br.com.forum.service.UserService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/usuarios")
class UserController(private val service: UserService) {

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid form: NovoUsuarioForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<UsuarioView> {
        val usuarioView = service.cadastrar(form)
        val uri = uriBuilder.path("/usuarios/${usuarioView.id}").build().toUri()
        return ResponseEntity.created(uri).body(usuarioView)
    }

    @GetMapping
    fun listarUsuarios(): List<UsuarioView> =
        service.listarTodos()

    @GetMapping("/{email}")
    fun encontrarPorEmail(@PathVariable email: String): User =
        service.encontrarPorEmail(email)

    @PutMapping
    @Transactional
    fun atualizarUsuario(@RequestBody @Valid form: AtualizacaoUserForm): ResponseEntity<UsuarioView> {
        val usuarioView = service.atualizar(form)
        return ResponseEntity.ok(usuarioView)
    }

    @DeleteMapping("{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: UUID) =
        service.deletar(id)

}