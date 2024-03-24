package br.com.forum.service

import br.com.forum.model.Resposta
import br.com.forum.repository.RespostaRepository
import org.springframework.stereotype.Service

@Service
class RespostaService(
    private val respostaRepository: RespostaRepository,
    private val emailService: EmailService
) {

    fun salvar(resposta: Resposta) {
        respostaRepository.save(resposta)

        resposta.topico.autor?.email?.let {
            val tituloTopico = resposta.topico.titulo

            emailService.notificar(
                subject = "Resposta recebida",
                text = "Olá, o seu tópico '$tituloTopico' foi respondido.",
                targetEmail = it
            )
        }
    }

}