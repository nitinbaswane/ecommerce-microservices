package com.ecomm.product.dto;


import com.ecomm.product.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    @NotBlank(message = "name should not be empty")
    private String name;
    @NotBlank(message = "description should not be empty")
    private String description;

    @Positive(message = "price must be positive number")
    private double price;

    private double discountPrice;
    @Min(value = 0, message = "quantity should be 0 or positive")
    private int quantity;
    private String brand;
    private String imageUrl;
    @NotBlank(message = "category should not be empty")
    private Long categoryId;
}
