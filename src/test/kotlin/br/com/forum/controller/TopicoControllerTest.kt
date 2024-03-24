package br.com.forum.controller

import br.com.forum.model.IntegrationTest
import br.com.forum.model.UserTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.web.context.WebApplicationContext

@AutoConfigureMockMvc
@SpringBootTest
class TopicoControllerTest: IntegrationTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var mockMvc: MockMvc

    private val user = UserTest.build()

    companion object {

        private const val RECURSO = "/topicos"
        private const val REGISTRO = "/usuarios"

    }

    @Test
    fun `deve chamar codigo 200 quando registrar usuario`() {
        val requestBody = """{ "email": "${user.email}", "password": "${user.password}", "role": "${user.role.name}" }"""

        mockMvc.post(REGISTRO) {
            contentType = MediaType.APPLICATION_JSON
            content = requestBody
        }.andExpect { status { is2xxSuccessful() } }
    }

    @Test
    fun `deve retornar codigo 400 quando chamar topicos sem token`() {
        mockMvc.get(RECURSO).andExpect { status { is4xxClientError() } }
    }

}