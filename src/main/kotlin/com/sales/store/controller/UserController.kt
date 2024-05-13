package com.sales.store.controller

import com.sales.store.dto.UserDTO
import com.sales.store.model.User
import com.sales.store.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/users")
class UserController {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @Autowired
    private lateinit var userService: UserService

    @PutMapping("/update")
    fun updateProfile(@RequestBody userDTO: UserDTO): User {
        logger.info("Updating user profile")
        return userService.updateUser(userDTO)
    }
}