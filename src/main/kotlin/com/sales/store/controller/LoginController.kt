package com.sales.store.controller

import com.sales.store.dto.LoginRequestDTO
import com.sales.store.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping

import org.slf4j.LoggerFactory

@Controller
@RequestMapping("/")
class LoginController(private val userService: UserService) {

    private val logger = LoggerFactory.getLogger(LoginController::class.java)

    @GetMapping("/login")
    fun loginPage(model: Model): String {
        model.addAttribute("loginRequest", LoginRequestDTO())
        return "login"
    }

    @PostMapping("/login")
    fun authenticateUser(@ModelAttribute("loginRequest") loginRequest: LoginRequestDTO, model: Model): String {
        logger.info("Received login request for user: ${loginRequest.username}")

        val isValidUser = userService.validateUser(loginRequest.username, loginRequest.password)

        logger.info("Is valid user: $isValidUser")

        if (isValidUser) {
            model.addAttribute("message", "User logged in successfully")
            return "index"
        } else {
            model.addAttribute("message", "Invalid username or password")
            return "login"
        }
    }
}