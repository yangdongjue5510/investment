package com.fastcampus.investment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
