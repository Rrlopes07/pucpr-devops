package br.com.forum.service

import br.com.forum.exception.NotFoundException
import br.com.forum.mapper.TopicoFormMapper
import br.com.forum.model.TopicoTest
import br.com.forum.model.TopicoViewTest
import br.com.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicoServiceTest {

    val topicos = PageImpl(listOf(TopicoTest.build()))

    val paginacao: Pageable = mockk()

    val topicoView: TopicoViewTest = mockk()

    val topicoViewConcreto = TopicoViewTest.build()

    val topicoRepository: TopicoRepository = mockk {
        every { findAll(paginacao) } returns topicos
        every { findByCursoNome(any(), any()) } returns topicos
    }

    val topicoFormMapper: TopicoFormMapper = mockk()

    val topicoService = TopicoService(
        topicoRepository, topicoFormMapper
    )

    @Test
    fun `deve listar tópicos a partir do nome do curso`() {
        topicoService.listar("Kotlin avançado", paginacao)

        verify(exactly = 1) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 0) { topicoRepository.findAll() }
    }

    @Test
    fun `deve listar todos os topicos do curso quando o nome do curso for nulo`() {
        topicoService.listar(null, paginacao)

        verify(exactly = 0) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoRepository.findAll(paginacao) }
    }

    @Test
    fun `deve retornar um topico view ao buscar por id`() {

    }

    @Test
    fun `deve listar not found exception quando topico nao for encontrado`() {
        every { topicoRepository.findById(any()) } returns Optional.empty()

        val atual = assertThrows<NotFoundException> {
            topicoService.buscarPorId(456)
        }

        assertThat(atual.message).isEqualTo("Tópico não localizado")
    }

}