package com.sales.store.controller

import com.sales.store.dto.SaleDTO
import com.sales.store.model.Sale
import com.sales.store.service.SaleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/sales")
class SaleController {

    @Autowired
    private lateinit var saleService: SaleService

    @GetMapping("/all")
    fun getAllSales(): List<Sale> {
        return saleService.getAllSales()
    }

    @GetMapping("/{id}")
    fun getSaleById(@PathVariable id: Int): Sale? {
        return saleService.getSaleById(id)
    }

    @PostMapping("/add")
    fun addSale(@RequestBody saleDTO: SaleDTO): Sale {
        return saleService.addSale(saleDTO)
    }

    @PutMapping("/{id}")
    fun updateSale(@PathVariable id: Int, @RequestBody sale: Sale): Sale {
        sale.id = id
        return saleService.updateSale(sale)
    }

    @DeleteMapping("/{id}")
    fun deleteSale(@PathVariable id: Int) {
        saleService.deleteSaleById(id)
    }
}
