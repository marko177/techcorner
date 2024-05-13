package com.sales.store.repository

import com.sales.store.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {

    fun findByUsername(username: String): User?

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    fun findByUsernameAndPassword(@Param("username") username: String?, @Param("password") password: String?): User?
}