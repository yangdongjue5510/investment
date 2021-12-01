package com.fastcampus.investment.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fastcampus.investment.domain.Products;

@RunWith(SpringRunner.class)
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
		//12월 1일 기준 2개 유효
		List<Products> validProducts = productsService.findValidProducts();
		Assertions.assertEquals(2, validProducts.size());
	}
}