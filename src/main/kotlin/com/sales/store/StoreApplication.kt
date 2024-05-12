package com.sales.store

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.domain.EntityScan

@SpringBootApplication
@EntityScan("com.sales.store.model") // Add this line
class StoreApplication

fun main(args: Array<String>) {
    runApplication<StoreApplication>(*args)
}