package com.mayank.RUST.Service;



import com.mayank.RUST.DTO.SaleItemDTO;
import com.mayank.RUST.DTO.SaleRequestDTO;
import com.mayank.RUST.Model.Product;
import com.mayank.RUST.Model.Sale;
import com.mayank.RUST.Model.SaleItem;
import com.mayank.RUST.Repository.ProductRepository;
import com.mayank.RUST.Repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleServiceImp implements SaleService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

//    @Override
//    public Sale createSale(SaleRequestDTO saleRequest) {
//        Sale sale = new Sale();
//        sale.setDate(Date.valueOf(LocalDate.now()).toLocalDate());
//        List<SaleItem> saleItems = new ArrayList<>();
//        double totalAmount = 0.0;
//
//        for (SaleItemDTO itemDTO : saleRequest.getItems()) {
//            Product product = productRepository.findById(itemDTO.getProductId())
//                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
//
//            if (product.getQuantity() < itemDTO.getQuantity()) {
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient stock for product: " + product.getName());
//            }
//
//            product.setQuantity(product.getQuantity() - itemDTO.getQuantity());
//            productRepository.save(product);
//
//            double productPrice = product.getPrice(); // fetch from DB
//
//            SaleItem saleItem = new SaleItem();
//            saleItem.setProduct(product);
//            saleItem.setQuantity(itemDTO.getQuantity());
//            saleItem.setPrice(productPrice);
//            saleItem.setSale(sale);
//
//            saleItems.add(saleItem);
//            totalAmount += productPrice * itemDTO.getQuantity();
//        }
//
//        sale.setItems(saleItems);
//        sale.setTotalAmount(totalAmount);
//
//        return saleRepository.save(sale);
//    }


    @Override
    @Transactional
    public Sale createSale(SaleRequestDTO saleRequest) {
        Sale sale = new Sale();
        sale.setDate(LocalDate.now());

        List<SaleItem> saleItems = new ArrayList<>();
        double totalAmount = 0.0;

        for (SaleItemDTO itemDTO : saleRequest.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

            if (product.getQuantity() < itemDTO.getQuantity()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient stock for product: " + product.getName());
            }

            // ⚠️ Don't save product here — just modify its state
            product.setQuantity(product.getQuantity() - itemDTO.getQuantity());

            SaleItem saleItem = new SaleItem();
            saleItem.setProduct(product); // ✅ managed object
            saleItem.setQuantity(itemDTO.getQuantity());
            saleItem.setPrice(product.getPrice());
            saleItem.setSale(sale);

            saleItems.add(saleItem);
            totalAmount += product.getPrice() * itemDTO.getQuantity();
        }

        sale.setItems(saleItems);
        sale.setTotalAmount(totalAmount);

        return saleRepository.save(sale); // ✅ Will cascade and flush all changes, including product
    }




    @Override
    public List<Sale> getAllSales(){
        return saleRepository.findAll();
    }

    @Override
    public Sale getSaleById(Long id) {
        return saleRepository.findById(id).orElseThrow(() -> new RuntimeException("Sale not found with id: " + id));
    }


}