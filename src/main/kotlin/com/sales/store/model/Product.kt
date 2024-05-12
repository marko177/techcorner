package com.sales.store.model

import jakarta.persistence.*

@Entity
@Table(name = "product")
class Product {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Basic
    @Column(name = "sku", nullable = false, unique = true)
    var sku: String? = null

    @Basic
    @Column(name = "description", nullable = false)
    var description: String? = null

    @Basic
    @Column(name = "stock", nullable = false)
    var stock: Int? = null

    @Basic
    @Column(name = "price", nullable = false)
    var price: Double? = null

    @Basic
    @Column(name = "category", nullable = false)
    var category: String? = null

    @Basic
    @Column(name = "image", nullable = true)
    var image: String? = null
}