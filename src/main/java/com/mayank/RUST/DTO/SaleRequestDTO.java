package com.mayank.RUST.DTO;


import java.util.List;

public class SaleRequestDTO {
    private List<SaleItemDTO> items;

    public List<SaleItemDTO> getItems() {
        return items;
    }

    public void setItems(List<SaleItemDTO> items) {
        this.items = items;
    }
}