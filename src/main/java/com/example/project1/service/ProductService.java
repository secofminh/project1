package com.example.project1.service;

import com.example.project1.dto.*;
import com.example.project1.entity.Category;
import com.example.project1.entity.ProductEntity;
import com.example.project1.exception.ProductException;
import com.example.project1.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    public Object getAllCategoryMinimumPriceByBrand() {
        List<String> brands = productRepository.findDistinctBrand();

        List<PriceAndBrandResponseDto> priceAndBrandResponseDtos = new ArrayList<>();

        for (String brand : brands) {
            Long totalPrice = 0L;
            for (Category category : Category.values()) {
                List<ProductEntity> productEntities = productRepository.findByBrandAndCategory(brand, category);

                ProductEntity minimumProduct = productEntities.stream()
                        .min(ProductEntity::priceDiff)
                        .get();

                totalPrice += minimumProduct.getPrice();
            }
            priceAndBrandResponseDtos.add(PriceAndBrandResponseDto.of(brand, totalPrice));
        }


        return priceAndBrandResponseDtos.stream()
                .min(PriceAndBrandResponseDto::totalPriceDiff)
                .get();
    }

    public Object getMinAndMaxPriceByCategory(String category) {
        Category categoryEnum = null;

        for (Category category1 : Category.values()) {
            if (category1.toString().equals(category)) {
                categoryEnum = category1;
            }
        }

        if (categoryEnum == null) {
            throw new ProductException("해당 카테고리는 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }

        List<ProductEntity> productEntities = productRepository.findByCategory(categoryEnum);

        ProductEntity minimumProduct = productEntities.stream()
                .min(ProductEntity::priceDiff)
                .get();

        ProductEntity maximumProduct = productEntities.stream()
                .max(ProductEntity::priceDiff)
                .get();

        return MinAndMaxPriceProductResponseDto.of(maximumProduct.getBrand(), maximumProduct.getPrice(), minimumProduct.getBrand(), minimumProduct.getPrice());
    }

    public Object createProduct(CreateProductRequestDto createProductRequestDto) {
        ProductEntity productEntity = ProductEntity.from(createProductRequestDto);
        return productRepository.save(productEntity);
    }

    public Object updateProduct(UpdateProductRequestDto updateProductRequestDto) {
        if (updateProductRequestDto.getId() == null) {
            throw new ProductException("수정 하기 위해 상품 ID는 필수 값입니다.", HttpStatus.CONFLICT);
        }

        productRepository.findById(updateProductRequestDto.getId())
                .orElseThrow(() -> new ProductException("해당 상품은 존재하지 않습니다.", HttpStatus.NOT_FOUND));

        ProductEntity productEntity = ProductEntity.from(updateProductRequestDto);
        return productRepository.save(productEntity);
    }

    public Object deleteProduct(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new ProductException("해당 상품은 존재하지 않습니다.", HttpStatus.NOT_FOUND));
        productRepository.deleteById(id);
        return id;
    }
}
