package com.sales.store.controller

import com.sales.store.dto.ProductDTO
import com.sales.store.model.Product
import com.sales.store.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController {

    @Autowired
    private lateinit var productService: ProductService

    @PostMapping("/add")
    fun addProduct(@RequestBody productDTO: ProductDTO): Product {
        return productService.addProduct(productDTO)
    }

    @GetMapping("/all")
    fun getAllProducts(): List<Product> {
        return productService.getAllProducts().sortedBy { it.id }
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Int): Product {
        return productService.getProductById(id)
    }

    @PutMapping("/update")
    fun updateProduct(@RequestBody productDTO: ProductDTO): Product {
        return productService.updateProduct(productDTO.id!!, productDTO)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteProduct(@PathVariable id: Int): ResponseEntity<Any> {
        productService.deleteProduct(id)
        return ResponseEntity.ok().body(mapOf("message" to "Product deleted successfully"))
    }

    @GetMapping("/search/{sku}")
    fun searchProduct(@PathVariable sku: String): Product {
        return productService.getProductBySku(sku)
    }
}
