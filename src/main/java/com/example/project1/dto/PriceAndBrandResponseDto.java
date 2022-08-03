package com.example.project1.dto;

import com.example.project1.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PriceAndBrandResponseDto {
    private String brand;
    private Long totalPrice;

    public static PriceAndBrandResponseDto of(String brand, Long totalPrice) {
        return new PriceAndBrandResponseDto(brand, totalPrice);
    }

    public int totalPriceDiff(final PriceAndBrandResponseDto other){
        return (int) (totalPrice - other.totalPrice);
    }
}
