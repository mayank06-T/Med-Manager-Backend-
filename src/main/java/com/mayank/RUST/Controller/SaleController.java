package com.mayank.RUST.Controller;

import com.mayank.RUST.DTO.SaleItemDTO;
import com.mayank.RUST.DTO.SaleRequestDTO;
import com.mayank.RUST.Model.Sale;
import com.mayank.RUST.Repository.SaleItemRepository;
import com.mayank.RUST.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {


    @Autowired
    private SaleService saleservice;

    @PostMapping
    public ResponseEntity<?> createSale(@RequestBody SaleRequestDTO saleRequest) {
        try {
            System.out.println("📥 Incoming Sale Items:");
            for (SaleItemDTO item : saleRequest.getItems()) {
                System.out.println("ID: " + item.getProductId() + " Qty: " + item.getQuantity() + " Price: " + item.getPrice());
            }

            Sale sale = saleservice.createSale(saleRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(sale);
        } catch (ResponseStatusException e) {
            System.err.println(" Sale failed: " + e.getReason());
            return ResponseEntity.status(e.getStatusCode()).body("Error: " + e.getReason());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Server error: " + e.getMessage());
        }
    }




    @GetMapping("/")
    public List<Sale> getAllSales() {
        return saleservice.getAllSales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long id) {
        Sale sale = saleservice.getSaleById(id);
        return ResponseEntity.ok(sale);
    }

}
