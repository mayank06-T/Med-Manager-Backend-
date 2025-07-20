package com.mayank.RUST.Service;

import com.mayank.RUST.DTO.SaleRequestDTO;
import com.mayank.RUST.Model.Sale;

import java.util.List;

public interface SaleService {
    Sale createSale(SaleRequestDTO saleRequest);

    List<Sale> getAllSales();

    Sale getSaleById(Long id);
}
