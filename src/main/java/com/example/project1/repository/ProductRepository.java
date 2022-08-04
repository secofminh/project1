package com.example.project1.repository;

import com.example.project1.entity.Category;
import com.example.project1.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByCategory(Category category);

    // DB에 존재하는 Brand를 중복 없이 가져오기 위한 Query
    @Query(value = "SELECT DISTINCT p.brand FROM ProductEntity p")
    List<String> findDistinctBrand();

    List<ProductEntity> findByBrandAndCategory(String brand, Category category);

}
