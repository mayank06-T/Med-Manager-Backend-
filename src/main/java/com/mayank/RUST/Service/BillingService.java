package com.mayank.RUST.Service;

import com.mayank.RUST.DTO.InvoiceDTO;
import com.mayank.RUST.DTO.ItemDTO;
import com.mayank.RUST.Model.Sale;
import com.mayank.RUST.Repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class BillingService {
    @Autowired
    private SaleRepository saleRepository;

    public InvoiceDTO generateInvoiceDTO(Long saleId) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sale not found"));

        InvoiceDTO dto = new InvoiceDTO();
        dto.setSaleId(sale.getId());
        dto.setDate(LocalDate.now().toString());
        dto.setTotalAmount(sale.getTotalAmount());

        List<ItemDTO> itemDTOs = sale.getSaleItems().stream().map(item -> {
            ItemDTO i = new ItemDTO();
            i.setProductName(item.getProduct().getName());
            i.setQuantity(item.getQuantity());
            i.setPrice(item.getPrice());
            return i;
        }).toList();

        dto.setItems(itemDTOs);

        return dto;
    }
}
