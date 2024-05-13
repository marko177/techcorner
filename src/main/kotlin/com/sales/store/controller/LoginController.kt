package com.sales.store.controller

import com.sales.store.dto.LoginRequestDTO
import com.sales.store.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import org.slf4j.LoggerFactory

@RestController
@RequestMapping("/")
class LoginController(private val userService: UserService) {

    private val logger = LoggerFactory.getLogger(LoginController::class.java)

    @PostMapping("/login")
    fun authenticateUser(@RequestBody loginRequest: LoginRequestDTO): ResponseEntity<Any> {
        logger.info("Received login request for user: ${loginRequest.username}")

        val isValidUser = userService.validateUser(loginRequest.username, loginRequest.password)

        logger.info("Is valid user: $isValidUser")

        return if (isValidUser) {
            ResponseEntity.ok(mapOf("message" to "User logged in successfully"))
        } else {
            ResponseEntity.status(401).body(mapOf("message" to "Invalid username or password"))
        }
    }
}