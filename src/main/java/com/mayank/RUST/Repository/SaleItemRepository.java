package com.mayank.RUST.Repository;

import com.mayank.RUST.Model.Product;
import com.mayank.RUST.Model.SaleItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem,Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM SaleItem s WHERE s.product = :product")
    void deleteByProduct(@Param("product") Product product);
}

