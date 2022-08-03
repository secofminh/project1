package com.example.project1.dto;

import com.example.project1.entity.Category;
import com.example.project1.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseDto {
    private Category category;
    private String brand;
    private Long price;

    public static ProductResponseDto from(ProductEntity productEntity) {
        return new ProductResponseDto(productEntity.getCategory(), productEntity.getBrand(), productEntity.getPrice());
    }
}
