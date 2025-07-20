package com.mayank.RUST.Controller;

import com.mayank.RUST.DTO.InvoiceDTO;
import com.mayank.RUST.Model.Invoice;
import com.mayank.RUST.Service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/billing")
public class BillingController {
    @Autowired
    private BillingService billingService;

    @GetMapping("/invoice/{saleId}")
    public ResponseEntity<InvoiceDTO> getInvoice(@PathVariable Long saleId) {
        return ResponseEntity.ok(billingService.generateInvoiceDTO(saleId));
    }
}
