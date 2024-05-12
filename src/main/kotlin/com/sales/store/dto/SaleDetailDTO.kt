package com.sales.store.dto

import com.sales.store.model.Product
import com.sales.store.model.Sale

class SaleDetailDTO {
    var id: Int? = null
    var sale: Sale? = null
    var product: Product? = null
    var quantity: Int? = null
}
