package com.ecomm.product.mapper;

import com.ecomm.product.dto.ProductDto;
import com.ecomm.product.entity.Category;
import com.ecomm.product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .discountPrice(product.getDiscountPrice())
                .quantity(product.getQuantity())
                .brand(product.getBrand())
                .imageUrl(product.getImageUrl())
                .categoryId(
                        product.getCategory() != null ?product.getCategory().getId() : null
                )
                .build();
    }
    public Product toEntity(ProductDto dto, Category category) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .discountPrice(dto.getDiscountPrice())
                .quantity(dto.getQuantity())
                .brand(dto.getBrand())
                .imageUrl(dto.getImageUrl())
                .category(category).build();
    }
}
