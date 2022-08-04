package com.example.project1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MinAndMaxPriceProductResponseDto {
    private String maximumBrand;
    private Long maximumPrice;
    private String minimumBrand;
    private Long minimumPrice;

    public static MinAndMaxPriceProductResponseDto of(String maximumBrand, Long maximumPrice, String minimumBrand, Long minimumPrice) {
        return new MinAndMaxPriceProductResponseDto(maximumBrand, maximumPrice, minimumBrand, minimumPrice);
    }
}
