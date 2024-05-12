package com.sales.store.service

import com.sales.store.dto.ProductDTO
import com.sales.store.model.Product
import com.sales.store.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService {

    @Autowired
    private lateinit var productRepository: ProductRepository

    fun getAllProducts(): List<Product> {
        // Get all products using the repository
        return productRepository.findAll()
    }

    fun addProduct(productDTO: ProductDTO): Product {
        val product = Product()
        product.sku = productDTO.sku
        product.description = productDTO.description
        product.category = productDTO.category
        product.stock = productDTO.stock
        product.price = productDTO.price

        return productRepository.save(product)
    }

    fun getProductById(id: Int): Product {
        // Get the product by id using the repository
        return productRepository.findById(id).orElseThrow { throw RuntimeException("Product not found") }
    }

    fun updateProduct(id: Int, productDTO: ProductDTO): Product {
        // Get the product by id using the repository
        val product = productRepository.findById(id).orElseThrow { throw RuntimeException("Product not found") }

        // Update the product
        product.sku = productDTO.sku
        product.description = productDTO.description
        product.category = productDTO.category
        product.stock = productDTO.stock
        product.price = productDTO.price

        // Save the updated product using the repository
        return productRepository.save(product)
    }

    fun deleteProduct(id: Int) {
        productRepository.deleteById(id)
    }

    fun getProductBySku(sku: String): Product {
        return productRepository.findBySku(sku)
    }
}