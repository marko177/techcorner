package com.sales.store.controller

import com.sales.store.model.SaleDetail
import com.sales.store.service.SaleDetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/sale-details")
class SaleDetailController{

    @Autowired
    private lateinit var saleDetailService: SaleDetailService

    @GetMapping("/sale/{saleId}")
    fun getSaleDetailsBySaleId(@PathVariable saleId: Int): List<SaleDetail> {
        return saleDetailService.getSaleDetailsBySaleId(saleId)
    }

    @PostMapping("/")
    fun addSaleDetail(@RequestBody saleDetail: SaleDetail): SaleDetail {
        return saleDetailService.addSaleDetail(saleDetail)
    }

    @PostMapping("/add-multiple")
    fun addSaleDetails(@RequestBody saleDetails: List<SaleDetail>): List<SaleDetail> {
        return saleDetailService.addSaleDetails(saleDetails)
    }
}
