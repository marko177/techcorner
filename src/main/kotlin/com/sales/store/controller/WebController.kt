package com.sales.store.controller

import com.sales.store.service.ProductService
import com.sales.store.service.SaleService
import com.sales.store.service.SaleDetailService
import jakarta.servlet.http.HttpSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class WebController {

    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var saleService: SaleService

    @Autowired
    private lateinit var saleDetailService: SaleDetailService


    @RequestMapping("/index")
    fun home(model: Model, session: HttpSession): String {
        return "index"
    }

    @RequestMapping("/login")
    fun login(model: Model, session: HttpSession): String {
        return "login"
    }

    @RequestMapping("/products")
    fun products(model: Model, session: HttpSession): String {
        val products = productService.getAllProducts()
        model.addAttribute("products", products)
        return "products"
    }

    @RequestMapping("/sales")
    fun sales(model: Model, session: HttpSession): String {
        val sales = saleService.getAllSales()
//        val saleDetails = saleDetailService.getAllSaleDetails()
        model.addAttribute("sales", sales)
//        model.addAttribute("saleDetails", saleDetails)

        return "sales"
    }

    @RequestMapping("/profile")
    fun reports(model: Model, session: HttpSession): String {
        return "profile"
    }

    @RequestMapping("/register")
    fun about(model: Model, session: HttpSession): String {
        return "register"
    }

    @RequestMapping("/logout")
    fun logout(model: Model, session: HttpSession): String {
        session.invalidate()
        return "index"
    }

    @RequestMapping("/pointofsale")
    fun pointOfSale(model: Model, session: HttpSession): String {

        val products = productService.getAllProducts()
        model.addAttribute("products", products)
        return "pointofsale"
    }

}