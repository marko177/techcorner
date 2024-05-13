package com.sales.store.controller

import com.sales.store.dto.UserDTO
import com.sales.store.service.ProductService
import com.sales.store.service.SaleService
import com.sales.store.service.UserService
import jakarta.servlet.http.HttpSession
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.security.core.userdetails.UserDetails


@Controller
@RequestMapping("/")
class WebController {

    private val logger = LoggerFactory.getLogger(WebController::class.java)

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var saleService: SaleService


    fun addUserToModel(model: Model, authentication: Authentication) {
        val principal = authentication.principal

        if (principal is UserDetails) {
            logger.info("User is logged in: ${principal.username}")
            val user = userService.getUserByUsername(principal.username)
            if (user != null) {
                model.addAttribute("user", user)
            } else {
                val guest = UserDTO(
                    id = 0,
                    username = "Guest",
                    name = "Guest",
                    lastName = "Guest"
                )
                model.addAttribute("user", guest)
            }
        }
    }

    @RequestMapping("/", "/index")
    fun index(model: Model, authentication: Authentication?): String {
        if (authentication != null) {
            addUserToModel(model, authentication)
        }

        val monthlyEarnings = saleService.getMonthlyEarnings()
        val yearlyEarnings = saleService.getYearlyEarnings()
        model.addAttribute("monthlyEarnings", monthlyEarnings)
        model.addAttribute("yearlyEarnings", yearlyEarnings)


        return "index"
    }

    @RequestMapping("/logincustom")
    fun login(model: Model, session: HttpSession): String {
        return "login"
    }

    @RequestMapping("/products")
    fun products(model: Model, authentication: Authentication?): String {

        if (authentication != null) {
            addUserToModel(model, authentication)
        }
        val products = productService.getAllProducts()
        model.addAttribute("products", products)
        return "products"
    }

    @RequestMapping("/sales")
    fun sales(model: Model, authentication: Authentication?): String {

        if (authentication != null) {
            addUserToModel(model, authentication)
        }

        val sales = saleService.getAllSales()
        model.addAttribute("sales", sales)

        return "sales"
    }

    @RequestMapping("/profile")
    fun profile(model: Model, authentication: Authentication?): String {

        if (authentication != null) {
            addUserToModel(model, authentication)
        }

        return "profile"
    }

    @RequestMapping("/register")
    fun about(model: Model, session: HttpSession): String {
        logger.info("Handling /register endpoint")
        return "register"
    }

    @RequestMapping("/logout")
    fun logout(model: Model, session: HttpSession): String {

        session.invalidate()
        return "index"
    }

    @RequestMapping("/pointofsale")
    fun pointOfSale(model: Model, authentication: Authentication?): String {

        if (authentication != null) {
            addUserToModel(model, authentication)
        }

        val products = productService.getAllProducts()
        model.addAttribute("products", products)
        return "pointofsale"
    }


}