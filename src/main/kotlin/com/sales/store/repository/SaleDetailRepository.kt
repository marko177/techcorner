package com.sales.store.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.sales.store.model.SaleDetail

@Repository
interface SaleDetailRepository : JpaRepository<SaleDetail, Int> {
    fun findBySaleId(saleId: Int): List<SaleDetail>
}
