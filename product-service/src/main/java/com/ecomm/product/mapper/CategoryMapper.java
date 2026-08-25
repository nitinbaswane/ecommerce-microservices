package com.ecomm.product.mapper;

import com.ecomm.product.dto.CategoryDto;
import com.ecomm.product.entity.Category;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDto toDto(Category category){

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .parentId(category.getParentId())
                .build();
    }

    public Category toEntity(CategoryDto dto) {
        return Category.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .parentId(dto.getParentId())
                .build();
    }
}
