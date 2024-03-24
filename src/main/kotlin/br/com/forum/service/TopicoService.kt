package br.com.forum.service

import br.com.forum.dto.AtualizacaoTopicoForm
import br.com.forum.dto.NovoTopicoForm
import br.com.forum.dto.TopicoPorCategoria
import br.com.forum.dto.TopicoView
import br.com.forum.exception.NotFoundException
import br.com.forum.extension.toDto
import br.com.forum.mapper.TopicoFormMapper
import br.com.forum.repository.TopicoRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoService(
    private var repository: TopicoRepository,
    private val topicoFormMapper: TopicoFormMapper
) {

    @Cacheable(cacheNames = ["Topicos"], key = "#root.method.name")
    fun listar(
        nomeCurso: String?,
        paginacao: Pageable
    ): Page<TopicoView> {
        val topicos =  nomeCurso?.let {
            repository.findByCursoNome(nomeCurso, paginacao)
        } ?: repository.findAll(paginacao)

        return topicos.map { topico -> topico.toDto() }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id)
            .orElseThrow { NotFoundException("Tópico não localizado") }

        return topico.toDto()
    }

    @CacheEvict(value = ["Topicos"], allEntries = true)
    fun cadastrar(form: NovoTopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)

        return topico.toDto()
    }

    @CacheEvict(value = ["Topicos"], allEntries = true)
    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = repository.findById(form.id)
            .orElseThrow { NotFoundException("Tópico não localizado") }
            .also {
                it.titulo = form.titulo
                it.mensagem = form.mensagem
            }

        return topico.toDto()
    }

    @CacheEvict(value = ["Topicos"], allEntries = true)
    fun deletar(id: Long) =
        repository.deleteById(id)

    fun relatorio(): List<TopicoPorCategoria> =
        repository.relatorio()

}