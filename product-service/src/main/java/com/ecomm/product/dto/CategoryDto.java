package com.ecomm.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private Long id;
    @NotBlank(message = "category name should not be blank")
    private String name;
    private String description;
    private Long parentId;
}
