package com.fastcampus.investment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.investment.domain.Products;
import com.fastcampus.investment.repository.ProductsRepository;

@Service
public class ProductsService {

	@Autowired
	private ProductsRepository productsRepository;

	@Transactional
	public Products findProductsById(long id) {
		Products foundProducts = productsRepository.findById(id).get();
		return foundProducts;
	}
}
