package com.sales.store.service
import com.sales.store.dto.ProductDTO
import com.sales.store.dto.SaleDTO
import com.sales.store.model.Sale
import com.sales.store.model.SaleDetail
import com.sales.store.repository.SaleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class SaleService {

    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var saleDetailService: SaleDetailService

    @Autowired
    private lateinit var saleRepository: SaleRepository


    fun getAllSales(): List<Sale> {
        return saleRepository.findAll()
    }

    fun addSale(saleDTO: SaleDTO): Sale {
        val sale = Sale()
        sale.userId = saleDTO.userId
        sale.preTaxTotal = saleDTO.preTaxTotal
        sale.tax = saleDTO.tax
        sale.total = saleDTO.total
        sale.payment = saleDTO.payment
        sale.date = java.sql.Timestamp.valueOf(LocalDateTime.now())

        val savedSale = saleRepository.save(sale)

        saleDTO.saleDetails?.forEach { saleDetailDTO ->
            val saleDetail = SaleDetail()
            saleDetail.sale = savedSale
            saleDetail.product = saleDetailDTO.product
            saleDetail.quantity = saleDetailDTO.quantity
            saleDetailService.addSaleDetail(saleDetail)

            // Get the product associated with the SaleDetail
            val product = productService.getProductById(saleDetailDTO.product?.id!!)

            // Subtract the quantity sold from the product's stock
            product.stock = saleDetailDTO.quantity?.let { product.stock?.minus(it) }

            // Convert the product to a ProductDTO
            val productDTO = ProductDTO()
            productDTO.id = product.id
            productDTO.sku = product.sku
            productDTO.description = product.description
            productDTO.category = product.category
            productDTO.stock = product.stock
            productDTO.price = product.price


            // Save the updated product to the database
            productService.updateProduct(productDTO.id!!, productDTO)
        }

        return savedSale
    }

    fun updateSale(sale: Sale): Sale {
        return saleRepository.save(sale)
    }

    fun getSaleById(id: Int): Sale? {
        return saleRepository.findById(id).orElse(null)
    }

    fun deleteSaleById(id: Int) {
        saleRepository.deleteById(id)
    }
}
