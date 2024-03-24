package br.com.forum.dto

data class AuthenticationRequest(
    val email: String,
    val password: String
)
