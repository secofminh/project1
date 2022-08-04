package com.example.project1.controller;

import com.example.project1.entity.Category;
import com.example.project1.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Rest Api Controller
@RequiredArgsConstructor // Bean 생성자 주입을 위해
@RequestMapping("product") // API Uri Prefix
public class ProductController {
    private final ProductService productService; // Product Service 생성자 주입

    @GetMapping("/all/minimum")
    public ResponseEntity<?> getAllCategoryMinimumPrice(){
        return ResponseEntity.ok(productService.getAllCategoryMinimumPrice());
    }

    @GetMapping("all/minimum/brand")
    public ResponseEntity<?> getAllCategoryMinimumPriceByBrand(){
        return ResponseEntity.ok(productService.getAllCategoryMinimumPriceByBrand());
    }

    @GetMapping("price/min-and-max/{category}")
    public ResponseEntity<?> getMinAndMaxPriceByCategory(@PathVariable String category){
        return ResponseEntity.ok(productService.getMinAndMaxPriceByCategory(category));
    }
}
