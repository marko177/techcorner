package com.sales.store.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.sales.store.model.User

@Repository
interface UserRepository : JpaRepository<User, Int> {
    fun findByUsername(username: String): User?
}