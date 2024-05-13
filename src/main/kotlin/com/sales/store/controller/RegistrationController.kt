package com.sales.store.controller

import com.sales.store.dto.RegisterRequestDTO
import com.sales.store.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class RegistrationController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/register")
    fun register(): String {
        return "register"
    }

    @PostMapping("/register")
    fun registerUser(@RequestBody registerRequest: RegisterRequestDTO) {
        // Here you can add the logic to register the user
        // If registration is successful, return a success message
        // If registration fails, return an error message
    }
}