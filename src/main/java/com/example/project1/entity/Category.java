package com.example.project1.entity;


import com.example.project1.exception.ProductException;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.http.HttpStatus;

public enum Category {
    상의, 아우터, 바지, 스니커즈, 가방, 모자, 양말, 액세서리;

    @JsonCreator
    public static Category from(String value) {
        for (Category category : Category.values()) {
            if (category.toString().equals(value)) {
                return category;
            }
        }
        throw new ProductException("해당 카테고리는 존재하지 않습니다.", HttpStatus.NOT_FOUND);
    }
}
