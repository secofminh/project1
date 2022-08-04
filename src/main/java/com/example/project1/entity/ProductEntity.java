package com.example.project1.entity;

import com.example.project1.dto.CreateProductRequestDto;
import com.example.project1.dto.UpdateProductRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity // JPA Entity 와 Mapping
@Table(name = "product") // 테이블 명 지정
@Getter
@Setter
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 아이디 생성을 데이터베이스에 위임
    private Long id;

    @Column
    @Enumerated(EnumType.STRING) // Java의 Enum 을 Database 와 연동
    private Category category;

    @Column
    private String brand; // 브랜드 명

    @Column
    private Long price; // 상품 가격

    public ProductEntity() {

    }

    // Stream 을 이용하기 위한 Comparator 함수
    public int priceDiff(final ProductEntity other) {
        return (int) (price - other.price);
    }

    public ProductEntity(Category category, String brand, Long price) {
        this.category = category;
        this.brand = brand;
        this.price = price;
    }

    public static ProductEntity from(CreateProductRequestDto createProductRequestDto) {
        return new ProductEntity(createProductRequestDto.getCategory(), createProductRequestDto.getBrand(), createProductRequestDto.getPrice());
    }

    public static ProductEntity from(UpdateProductRequestDto updateProductRequestDto) {
        return new ProductEntity(updateProductRequestDto.getId(), updateProductRequestDto.getCategory(), updateProductRequestDto.getBrand(), updateProductRequestDto.getPrice());

    }
}
