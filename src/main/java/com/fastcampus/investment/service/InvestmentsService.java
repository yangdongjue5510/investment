package com.fastcampus.investment.service;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.investment.domain.InvestmentStatus;
import com.fastcampus.investment.domain.Investments;
import com.fastcampus.investment.domain.Products;
import com.fastcampus.investment.repository.InvestmentsRepository;

@Service
public class InvestmentsService {

	@Autowired
	InvestmentsRepository investmentsRepository;

	@Transactional
	public long countInvestment() {
		return investmentsRepository.count();
	}

	@Transactional
	public Long insertInvestment(Investments investments) {
		Investments insertedInvestment = investmentsRepository.save(investments);
		return insertedInvestment.getInvestId();
	}

	@Transactional
	public Long findInvestorCount(long productId) {
		return investmentByProductId(productId, investmentsRepository.findAll().stream())
			.filter(invest -> invest.getInvestmentStatus().equals(InvestmentStatus.VALID)).distinct().count();
	}

	@Transactional
	public long sumProductInvestments(long productId) {
		return investmentByProductId(productId, investmentsRepository.findAll().stream())
			.map(invest -> invest.getInvestmentAmount()).reduce(0L, Long::sum);
	}

	@Transactional
	public boolean checkAmountValidity(long moneyAmount, long productId) {
		Products product = productFromInvestment(
			investmentByProductId(productId, investmentsRepository.findAll().stream()))
			.findFirst().get();
		long sumAmount = sumProductInvestments(productId);
		if (moneyAmount + sumAmount < product.getTotalInvestingAmount()) {
			return true;
		}
		return false;
	}

	private Stream<Investments> investmentByProductId(long productId, Stream<Investments> stream) {
		return stream.filter(investments -> investments.getProduct().getId() == productId);
	}

	private Stream<Products> productFromInvestment(Stream<Investments> stream) {
		return stream.map(investments -> investments.getProduct());
	}
}
