package br.com.forum.dto

data class AuthenticationResponse(
    val accessToken: String,
    val refreshToken: String
)
