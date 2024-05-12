package com.sales.store.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.sales.store.model.Product

@Repository
interface ProductRepository : JpaRepository<Product, Int> {
    fun findBySku(sku: String): Product
}
