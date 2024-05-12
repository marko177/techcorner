package com.sales.store.model

import jakarta.persistence.*

@Entity
@Table(name = "sale_detail")
class SaleDetail {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    var sale: Sale? = null

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    var product: Product? = null

    @Basic
    @Column(name = "quantity", nullable = false)
    var quantity: Int? = null
}