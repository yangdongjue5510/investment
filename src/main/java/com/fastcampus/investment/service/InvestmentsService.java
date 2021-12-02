package com.fastcampus.investment.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.investment.domain.InvestmentStatus;
import com.fastcampus.investment.domain.Investments;
import com.fastcampus.investment.domain.Products;
import com.fastcampus.investment.domain.ResponseInvestments;
import com.fastcampus.investment.repository.InvestmentsRepository;

@Service
public class InvestmentsService {

	@Autowired
	InvestmentsRepository investmentsRepository;

	@Autowired
	ProductsService productsService;

	@Transactional
	public Investments insertInvestment(Investments investments) {
		Products product = productsService.findProductsById(investments.getProductId());
		investments.setProduct(product);
		Investments insertedInvestment = investmentsRepository.save(investments);
		return insertedInvestment;
	}

	@Transactional
	public Long findInvestorCount(long productId) {
		return investmentByProductId(productId, investmentsRepository.findAll().stream())
			.filter(invest -> invest.getStatus().equals(InvestmentStatus.INVESTED)).distinct().count();
	}

	@Transactional
	public long sumProductInvestments(long productId) {
		return investmentByProductId(productId, investmentsRepository.findAll().stream())
			.filter(invest -> invest.getStatus().equals(InvestmentStatus.INVESTED))
			.map(invest -> invest.getInvestAmount()).reduce(0L, Long::sum);
	}

	@Transactional
	public boolean checkAmountValidity(long moneyAmount, long productId) {
		Products product = productsService.findProductsById(productId);
		long sumAmount = sumProductInvestments(productId);
		if (moneyAmount + sumAmount < product.getTotalInvestAmount()) {
			return true;
		}
		return false;
	}

	@Transactional
	public List<ResponseInvestments> findInvestByUserId(long userId) {
		return investmentsRepository.findAll().stream().filter(invest -> invest.getUserId() == userId)
			.map(invest -> new ResponseInvestments(invest)).collect(Collectors.toList());
	}

	@Transactional
	long countInvestment() {
		return investmentsRepository.count();
	}

	private Stream<Investments> investmentByProductId(long productId, Stream<Investments> stream) {
		return stream.filter(investments -> investments.getProduct().getId() == productId);
	}

	private Stream<Products> productFromInvestment(Stream<Investments> stream) {
		return stream.map(investments -> investments.getProduct());
	}
}
