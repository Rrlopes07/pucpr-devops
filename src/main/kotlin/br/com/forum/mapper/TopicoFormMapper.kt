package br.com.forum.mapper

import br.com.forum.dto.NovoTopicoForm
import br.com.forum.model.Topico
import br.com.forum.service.CursoService
import br.com.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper(
    private val cursoService: CursoService,
    private val userService: UserService,
): Mapper<NovoTopicoForm, Topico> {

    override fun map(t: NovoTopicoForm): Topico =
        Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorId(t.idCurso),
            autor = userService.buscarPorId(t.idAutor)
        )

}