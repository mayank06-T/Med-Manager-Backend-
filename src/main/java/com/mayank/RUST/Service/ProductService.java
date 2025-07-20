package com.mayank.RUST.Service;

import com.mayank.RUST.Model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();

    Product getProductById(Long id);


    Product addProduct(Product product);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

//    boolean existsById(Long id);
}
