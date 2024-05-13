package com.sales.store.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.sales.store.model.Sale
import java.sql.Timestamp

@Repository
interface SaleRepository : JpaRepository<Sale, Int> {

    fun findAllByDateBetween(date: Timestamp, date2: Timestamp): List<Sale>
}
