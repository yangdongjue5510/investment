package com.fastcampus.investment.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fastcampus.investment.domain.Investments;

@RunWith(SpringRunner.class)
@SpringBootTest
class InvestmentsServiceTest {
	@Autowired
	InvestmentsService investmentsService;
	@Autowired
	ProductsService productsService;

	@Test
	void insertInvestment() {
		long countInvestment = investmentsService.countInvestment();
		Investments investments = new Investments();
		investments.setInvestmentAmount(1000L);
		investments.setProduct(productsService.findProductsById(1));
		investments.setUserId(1L);
		investmentsService.insertInvestment(investments);
		Assertions.assertEquals(investmentsService.countInvestment(), countInvestment+1L);
	}

	@Test
	void checkInvesterCount() {
		long invester = investmentsService.findInvestorCount(1);
		Investments investments = new Investments();
		investments.setInvestmentAmount(1000L);
		investments.setProduct(productsService.findProductsById(1));
		investments.setUserId(1L);
		investmentsService.insertInvestment(investments);
		Long investorCountAfterInsert = investmentsService.findInvestorCount(1);
		Assertions.assertEquals(investorCountAfterInsert, invester+1);
	}

	@Test
	void investValid() {
		Assertions.assertTrue(investmentsService.checkAmountValidity(1L, 1));
		Assertions.assertFalse(investmentsService.checkAmountValidity(1000000000000000L, 1));
	}
}