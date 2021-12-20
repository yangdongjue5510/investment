package com.fastcampus.investment.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.investment.domain.Products;
import com.fastcampus.investment.repository.ProductsRepository;

@Service
public class ProductsService {

	@Autowired
	private ProductsRepository productsRepository;

	@Autowired
	private InvestmentsService investmentsService;

	@Transactional
	public Products findProductsById(long id) {
		Products foundProducts = productsRepository.findById(id).get();
		return foundProducts;
	}

	@Transactional
	public List<Products> findValidProducts() {
		Date now = new Date();
		return productsRepository.findAll().stream().filter(products ->
			products.getStartedAt().before(now) && products.getFinishedAt().after(now)
		).collect(Collectors.toList());
	}

	@Transactional
	public void bindCountAndAmount(List<Products> list) {
		list.stream().forEach(products -> {
			products.setInvestedCount(investmentsService.findInvestorCount(products.getId()));
			products.setInvestedAmount(investmentsService.sumProductInvestments(products.getId()));
		});
	}
}
