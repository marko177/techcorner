package com.sales.store.dto

class SaleDTO {
    var id: Int? = null
    var userId: Int? = null
    var preTaxTotal: Double? = null
    var tax: Double? = null
    var total: Double? = null
    var payment: Double? = null
    var date: java.sql.Timestamp? = null
    var saleDetails: List<SaleDetailDTO>? = null
}