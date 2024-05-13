package com.sales.store.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.CacheControl
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.time.Duration


@Configuration
@EnableWebMvc
class WebConfig : WebMvcConfigurer {

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/").setViewName("index")
        registry.addViewController("/error").setViewName("error")
        registry.addViewController("/pointofsale").setViewName("pointofsale")
        registry.addViewController("/products").setViewName("products")
        registry.addViewController("/profile").setViewName("profile")
        registry.addViewController("/sales").setViewName("sales")
        registry.addViewController("/register").setViewName("register")
        registry.addViewController("/login").setViewName("login")
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/assets/**")
            .addResourceLocations("classpath:static/assets/")
            .setCacheControl(CacheControl.maxAge(Duration.ofDays(365)))

        registry.addResourceHandler("/js/**")
            .addResourceLocations("classpath:static/js/")
            .setCacheControl(CacheControl.maxAge(Duration.ofDays(365)))

        registry.addResourceHandler("/favicon.ico")
            .addResourceLocations("classpath:static/favicon.ico")
            .setCacheControl(CacheControl.maxAge(Duration.ofDays(365)))
    }
}