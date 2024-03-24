package br.com.forum.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "usuario")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID = UUID.randomUUID(),
    var email: String,
    var password: String,

    @Enumerated
    var role: Role
)
