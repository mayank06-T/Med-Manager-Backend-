package com.mayank.RUST.Controller;


import com.mayank.RUST.Model.Product;
import com.mayank.RUST.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public List<Product> getAllProducts() {

        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {

        Product product = service.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product saved = service.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updated = service.updateProduct(id, product);
        return ResponseEntity.ok(updated);
    }


//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
//        try {
//            service.deleteProduct(id); // Calls your service layer
//            return ResponseEntity.noContent().build(); // 204 No Content
//        } catch (DataIntegrityViolationException e) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(" Cannot delete: Product is linked to a sale.");
//        } catch (Exception e) {
//            return ResponseEntity
//                    .status(500)
//                    .body(" Unexpected error while deleting product.");
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            service.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Cannot delete: Product is linked to a sale.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error while deleting product. " + e.getMessage());
        }
    }



}