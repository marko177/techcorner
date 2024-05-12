package com.sales.store.service

import com.sales.store.dto.SaleDetailDTO
import com.sales.store.model.SaleDetail
import com.sales.store.repository.SaleDetailRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SaleDetailService {

    @Autowired
    private lateinit var saleDetailRepository: SaleDetailRepository

    fun addSaleDetail(saleDetail: SaleDetail): SaleDetail {
        return saleDetailRepository.save(saleDetail)
    }

    fun getSaleDetailsBySaleId(saleId: Int): List<SaleDetail> {
        return saleDetailRepository.findBySaleId(saleId)
    }

    fun addSaleDetails(saleDetails: List<SaleDetail>): List<SaleDetail> {
        return saleDetailRepository.saveAll(saleDetails)
    }
}
