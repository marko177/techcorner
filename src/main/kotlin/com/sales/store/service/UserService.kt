package com.sales.store.service

import com.sales.store.controller.WebController
import com.sales.store.dto.UserDTO
import com.sales.store.model.User
import com.sales.store.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    private val logger = LoggerFactory.getLogger(WebController::class.java)

    @Autowired
    private lateinit var userRepository: UserRepository


    fun userToDTO(user: User): UserDTO {
        return UserDTO(
            id = user.id,
            username = user.username,
            name = user.name,
            lastName = user.lastName
        )
    }

    fun getUserByUsername(username: String): UserDTO? {
        val user = userRepository.findByUsername(username)
        val userDTO = user?.let { userToDTO(it) }
        return userDTO
    }

    fun validateUser(username: String?, password: String?): Boolean {
        logger.info("UserService: Validating user")
        return userRepository.findByUsernameAndPassword(username, password) != null
    }

    fun updateUser(userDTO: UserDTO): User {
        logger.info("UserService: Updating user profile")
        logger.info("UserService: UserDTO: $userDTO")
        logger.info("UserService: UserDTO username: ${userDTO.username}")
        logger.info("UserService: UserDTO name: ${userDTO.name}")
        logger.info("UserService: UserDTO lastName: ${userDTO.lastName}")
        val user = userRepository.findByUsername(userDTO.username!!)
        user?.name = userDTO.name
        user?.lastName = userDTO.lastName
        return userRepository.save(user!!)
    }


}