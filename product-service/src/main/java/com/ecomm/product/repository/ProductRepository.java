package com.ecomm.product.repository;

import com.ecomm.product.entity.Category;
import com.ecomm.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    Page<Product> findByPriceBetween(double min, double max, Pageable pageable);

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("select p from Product p"+" WHERE LOWER(p.name) LIKE(lower(concat('%',:keyword,'%')))" +"OR LOWER(p.description) LIKE(lower(concat('%',:keyword,'%')))")
    Page<Product> searchProducts(@Param("keyword") String keyword,Pageable pageable);

    @Query("SELECT p FROM Product p "+
            "WHERE (:keyword IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword,'%'))) "+
            "AND (:categoryId IS NULL OR p.category.id= :categoryId) "+
            "AND (p.price BETWEEN :minPrice AND :maxPrice)")
    Page<Product> advanceFilter(
            @Param("keyword") String keyword,
            @Param("categoryId") Long categoryId,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            Pageable pageable
    );
}
