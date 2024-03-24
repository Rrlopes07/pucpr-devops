package br.com.forum.controller

import br.com.forum.model.Resposta
import br.com.forum.service.RespostaService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/respostas")
class RespostaController(
    private val respostaService: RespostaService
) {

    @PostMapping
    fun salvar(@RequestBody @Valid resposta: Resposta) =
        respostaService.salvar(resposta)

}