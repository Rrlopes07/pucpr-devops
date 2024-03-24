package br.com.forum.model

object UserTest {

    fun build() = User(email = "email@email.com", password = "senha123456", role = Role.ADMIN)

}