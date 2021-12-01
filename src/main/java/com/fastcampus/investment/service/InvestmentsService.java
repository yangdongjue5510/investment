package com.fastcampus.investment.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.investment.domain.InvestmentStatus;
import com.fastcampus.investment.domain.Investments;
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
		return investmentsRepository.findAll().stream()
			.filter(investments	-> investments.getProduct().getId() == productId
			&& investments.getInvestmentStatus().equals(InvestmentStatus.VALID)).distinct().count();
	}

}
