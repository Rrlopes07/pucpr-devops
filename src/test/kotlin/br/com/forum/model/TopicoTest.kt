package br.com.forum.model

import java.util.*

object TopicoTest {

    fun build() = Topico(
        id = 1,
        titulo = "Kotlin Basico",
        mensagem = "Aprendendo kotlin basico",
        curso = CursoTest.build(),
        autor = UserTest.build()
    )

}