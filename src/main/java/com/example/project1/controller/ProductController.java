package com.example.project1.controller;

import com.example.project1.dto.CreateProductRequestDto;
import com.example.project1.dto.UpdateProductRequestDto;
import com.example.project1.entity.Category;
import com.example.project1.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Rest Api Controller
@RequiredArgsConstructor // Bean 생성자 주입을 위해
@RequestMapping("product") // API Uri Prefix
public class ProductController {
    private final ProductService productService; // Product Service 생성자 주입

    @GetMapping("/all/minimum")
    public ResponseEntity<?> getAllCategoryMinimumPrice() {
        return ResponseEntity.ok(productService.getAllCategoryMinimumPrice());
    }

    @GetMapping("all/minimum/brand")
    public ResponseEntity<?> getAllCategoryMinimumPriceByBrand() {
        return ResponseEntity.ok(productService.getAllCategoryMinimumPriceByBrand());
    }

    @GetMapping("price/min-and-max/{category}")
    public ResponseEntity<?> getMinAndMaxPriceByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getMinAndMaxPriceByCategory(category));
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        return ResponseEntity.ok(productService.createProduct(createProductRequestDto));
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody UpdateProductRequestDto updateProductRequestDto) {
        return ResponseEntity.ok(productService.updateProduct(updateProductRequestDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

}
