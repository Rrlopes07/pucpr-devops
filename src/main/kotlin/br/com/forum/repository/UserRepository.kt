package br.com.forum.repository

import br.com.forum.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<User, UUID> {

    abstract fun findByEmail(username: String?): User?

}