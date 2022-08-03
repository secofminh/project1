package com.example.project1.service;

import com.example.project1.dto.MinimumPriceCombinationResponseDto;
import com.example.project1.dto.ProductResponseDto;
import com.example.project1.entity.Category;
import com.example.project1.entity.ProductEntity;
import com.example.project1.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Object getAllCategoryMinimumPrice() {

        List<ProductResponseDto> productEntities = new ArrayList<>();
        Long totalPrice = 0L;
        for (Category category : Category.values()) {
            // category 별 모든 상품을 담는 List
            List<ProductEntity> productEntityList = productRepository.findByCategory(category);

            // 최소(price) 값을 가진 product 얻기
            ProductEntity minimumProduct = productEntityList.stream()
                    .min(ProductEntity::priceDiff)
                    .get();

            totalPrice += minimumProduct.getPrice();

            productEntities.add(ProductResponseDto.from(minimumProduct));
        }

        return MinimumPriceCombinationResponseDto.of(productEntities, totalPrice);
    }
}
