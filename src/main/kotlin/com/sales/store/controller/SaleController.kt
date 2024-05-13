package com.sales.store.controller

import com.sales.store.dto.SaleDTO
import com.sales.store.model.Sale
import com.sales.store.service.SaleService
import com.sales.store.service.SaleDetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import com.itextpdf.text.Document
import com.itextpdf.text.Element
import com.itextpdf.text.Paragraph
import com.itextpdf.text.Phrase
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import java.io.ByteArrayOutputStream

@RestController
@RequestMapping("/api/sales")
class SaleController {

    @Autowired
    private lateinit var saleService: SaleService

    @Autowired
    private lateinit var saleDetailService: SaleDetailService


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

    @GetMapping("/receipt/{id}")
    fun getSaleReceipt(@PathVariable id: Int): ResponseEntity<ByteArray> {
        val sale = saleService.getSaleById(id)
        if (sale != null) {

            val document = Document()
            val byteArrayOutputStream = ByteArrayOutputStream()
            PdfWriter.getInstance(document, byteArrayOutputStream)

            document.open()

            val title = Paragraph("Sale Receipt")
            title.alignment = Element.ALIGN_CENTER
            document.add(title)

            val saleDetailsTable = PdfPTable(2)
            saleDetailsTable.widthPercentage = 100f
            saleDetailsTable.spacingBefore = 10f
            saleDetailsTable.spacingAfter = 10f

            saleDetailsTable.addCell("Seller")
            saleDetailsTable.addCell(sale.userId.toString())
            saleDetailsTable.addCell("Subtotal")
            saleDetailsTable.addCell(sale.preTaxTotal.toString())
            saleDetailsTable.addCell("Tax")
            saleDetailsTable.addCell(sale.tax.toString())
            saleDetailsTable.addCell("Total")
            saleDetailsTable.addCell(sale.total.toString())
            saleDetailsTable.addCell("Payment")
            saleDetailsTable.addCell(sale.payment.toString())
            saleDetailsTable.addCell("Date")
            saleDetailsTable.addCell(sale.date.toString())

            document.add(saleDetailsTable)

            val itemDetailsTable = PdfPTable(4)
            itemDetailsTable.widthPercentage = 100f
            itemDetailsTable.spacingBefore = 10f
            itemDetailsTable.spacingAfter = 10f

            val cell = PdfPCell(Phrase("Sale Details"))
            cell.colspan = 4
            cell.horizontalAlignment = Element.ALIGN_CENTER
            cell.setPadding(10.0f)
            itemDetailsTable.addCell(cell)

            itemDetailsTable.addCell("Product Name")
            itemDetailsTable.addCell("Quantity")
            itemDetailsTable.addCell("Price per Unit")
            itemDetailsTable.addCell("Total")

            val saleDetails = saleDetailService.getSaleDetailsBySaleId(id)

            for (detail in saleDetails) {
                itemDetailsTable.addCell(detail.product?.description)
                itemDetailsTable.addCell(detail.quantity.toString())
                itemDetailsTable.addCell(detail.product?.price.toString())
                itemDetailsTable.addCell((detail.quantity?.let { detail.product?.price?.times(it) }).toString())
            }

            document.add(itemDetailsTable)

            document.close()

            val headers = HttpHeaders()
            headers.contentType = MediaType.APPLICATION_PDF
            headers.setContentDispositionFormData("attachment", "sale_receipt_$id.pdf")

            return ResponseEntity(byteArrayOutputStream.toByteArray(), headers, HttpStatus.OK)
        } else {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
