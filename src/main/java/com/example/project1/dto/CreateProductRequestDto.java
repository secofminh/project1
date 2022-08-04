package com.example.project1.dto;

import com.example.project1.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateProductRequestDto {
    private Category category;
    private String brand;
    private Long price;

}
