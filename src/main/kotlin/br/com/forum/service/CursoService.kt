package br.com.forum.service

import br.com.forum.dto.CursoView
import br.com.forum.dto.NovoCursoForm
import br.com.forum.exception.NotFoundException
import br.com.forum.extension.toDto
import br.com.forum.extension.toModel
import br.com.forum.model.Curso
import br.com.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService(private val repository: CursoRepository) {

    fun buscarPorId(id: Long): Curso =
        repository.findById(id)
            .orElseThrow{ NotFoundException("Curso não encontrado") }

    fun cadastrar(form: NovoCursoForm): CursoView? {
        val curso = form.toModel()
        repository.save(curso)

        return curso.toDto()
    }

}
