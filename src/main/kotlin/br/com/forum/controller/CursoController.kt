package br.com.forum.controller

import br.com.forum.dto.CursoView
import br.com.forum.dto.NovoCursoForm
import br.com.forum.service.CursoService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/cursos")
class CursoController(private val service: CursoService) {

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid form: NovoCursoForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<CursoView> {
        val cursoView = service.cadastrar(form)
        val uri = uriBuilder.path("/cursos/${cursoView?.id}").build().toUri()
        return ResponseEntity.created(uri).body(cursoView)
    }

}