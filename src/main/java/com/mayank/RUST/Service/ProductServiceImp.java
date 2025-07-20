package com.mayank.RUST.Service;


import com.mayank.RUST.Model.Product;
import com.mayank.RUST.Repository.ProductRepository;
import com.mayank.RUST.Repository.SaleItemRepository;
import com.mayank.RUST.Repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService{

    @Autowired
    private ProductRepository prepo;
    @Autowired
    private SaleItemRepository srepo;
    @Autowired
    private SaleRepository slrepo;

    @Override
    public List<Product> getAllProducts() {
        return prepo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return prepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public Product addProduct(Product product) {
        return prepo.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existing = prepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        existing.setName(product.getName());
        existing.setExpire(product.getExpire());
        existing.setQuantity(product.getQuantity());
        existing.setPrice(product.getPrice());

        return prepo.save(existing);
    }

//    @Override
//    public void deleteProduct(Long id) {
//
//        Product product = prepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
//        prepo.delete(product);
//
//
//
//
//
//    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = prepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Remove all related SaleItems first
        srepo.deleteByProduct(product);

        // Now delete the product
        prepo.delete(product);
    }



}

