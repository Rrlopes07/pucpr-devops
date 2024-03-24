package br.com.forum.extension

import br.com.forum.dto.CursoView
import br.com.forum.dto.NovoCursoForm
import br.com.forum.model.Curso

fun NovoCursoForm.toModel(): Curso =
    Curso(nome = this.nome, categoria = this.categoria)

fun Curso.toDto(): CursoView =
    CursoView(this.id, this.nome)
