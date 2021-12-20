package com.fastcampus.investment.service;


import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fastcampus.investment.domain.Products;

@SpringBootTest
class ProductsServiceTest {
	@Autowired
	ProductsService productsService;

	@Test
	void findProductsById() {
		Products productsById = productsService.findProductsById(1L);
		Assertions.assertEquals(productsById.getTitle(), "You can be elon musk");
	}

	@Test
	void findValidProduct() {
		List<Products> validProducts = productsService.findValidProducts();
		Assertions.assertEquals(2, validProducts.size());
	}
}