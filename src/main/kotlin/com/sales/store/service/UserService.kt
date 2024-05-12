package com.sales.store.service

import com.sales.store.model.User
import com.sales.store.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository
) {
    fun getUserByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }

    fun saveUser(user: User): User {
        return userRepository.save(user)
    }
}