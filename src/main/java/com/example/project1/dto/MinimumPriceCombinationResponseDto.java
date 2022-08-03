package com.example.project1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MinimumPriceCombinationResponseDto {
    private List<ProductResponseDto> products;
    private Long totalPrice;

    public static MinimumPriceCombinationResponseDto of(List<ProductResponseDto> products, Long totalPrice) {
        return new MinimumPriceCombinationResponseDto(products, totalPrice);
    }
}
