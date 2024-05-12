package com.sales.store.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.sales.store.model.Sale

@Repository
interface SaleRepository : JpaRepository<Sale, Int> {
}
