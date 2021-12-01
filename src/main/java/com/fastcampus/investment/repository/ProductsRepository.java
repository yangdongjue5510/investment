package com.fastcampus.investment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcampus.investment.domain.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
