package com.fastcampus.investment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.investment.domain.InvestmentStatus;
import com.fastcampus.investment.domain.Investments;
import com.fastcampus.investment.domain.Products;
import com.fastcampus.investment.domain.ResponseInvestments;
import com.fastcampus.investment.repository.InvestmentsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvestmentsService {

	private final InvestmentsRepository investmentsRepository;

	private final ProductsService productsService;

	@Transactional
	public Investments insertInvestment(Investments investments) {
		Products product = productsService.findProductsById(investments.getProductId());
		investments.setProduct(product);
		Investments insertedInvestment = investmentsRepository.save(investments);
		return insertedInvestment;
	}

	@Transactional
	public Long findInvestorCount(long productId) {
		return investmentsRepository.findInvestmentsByIdAndStatusEquals(productId, InvestmentStatus.INVESTED)
			.stream().distinct().count();
	}

	@Transactional
	public long sumProductInvestments(long productId) {
		return investmentsRepository.findInvestmentsByIdAndStatusEquals(productId, InvestmentStatus.INVESTED)
			.stream().mapToLong(Investments::getInvestAmount).sum();
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
		return investmentsRepository.findInvestmentsByUserId(userId).stream()
			.map(invest -> new ResponseInvestments(invest)).collect(Collectors.toList());
	}

	@Transactional
	public Investments cancelInvestStatus(long userId, long productId) {
		Investments investment =
			investmentsRepository.findInvestmentsByUserIdAndIdAndStatusEquals(userId, productId,
				InvestmentStatus.INVESTED);
		investmentsRepository.updateInvestStatus(investment, InvestmentStatus.CANCELED);
		return investment;
	}

	@Transactional
	long countInvestment() {
		return investmentsRepository.count();
	}

	@Transactional
	public void bindCountAndAmount(List<Products> list) {
		list.stream().forEach(products -> {
			products.setInvestedCount(findInvestorCount(products.getId()));
			products.setInvestedAmount(sumProductInvestments(products.getId()));
		});
	}
}
