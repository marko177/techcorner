package com.sales.store.model

import jakarta.persistence.*

@Entity
@Table(name = "sale")
class Sale {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column(name = "user_id", nullable = false)
    var userId: Int? = null

    @Column(name = "pretaxtotal", nullable = false)
    var preTaxTotal: Double? = null

    @Column(name = "tax", nullable = false)
    var tax: Double? = null

    @Column(name = "total", nullable = false)
    var total: Double? = null

    @Column(name = "payment", nullable = false)
    var payment: Double? = null

    @Column(name = "date", nullable = false)
    var date: java.sql.Timestamp? = null
}
