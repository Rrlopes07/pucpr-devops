package br.com.forum.integration

import br.com.forum.dto.TopicoPorCategoria
import br.com.forum.model.IntegrationTest
import br.com.forum.model.TopicoTest
import br.com.forum.repository.TopicoRepository
import br.com.forum.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest

@SpringBootTest
class TopicoRepositoryTest: IntegrationTest {

    private val topico = TopicoTest.build()

    @Autowired
    private lateinit var topicoRepository: TopicoRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Test
    fun `deve gerar um relatorio`() {
        topico.autor = userRepository.findByEmail(topico.autor!!.email)

        topicoRepository.save(topico)

        val relatorio = topicoRepository.relatorio()

        assertThat(relatorio).isNotNull
        assertThat(relatorio.first()).isExactlyInstanceOf(TopicoPorCategoria::class.java)
    }

    @Test
    fun `deve listar topicos pelo nome do curso`() {
        val topico = topicoRepository.findByCursoNome(topico.curso.nome, PageRequest.of(0, 5))
        assertThat(topico).isNotNull
    }

}