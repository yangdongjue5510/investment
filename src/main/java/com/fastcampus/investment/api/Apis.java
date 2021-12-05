package com.fastcampus.investment.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fastcampus.investment.domain.InvestmentStatus;
import com.fastcampus.investment.domain.Investments;
import com.fastcampus.investment.domain.Products;
import com.fastcampus.investment.domain.ResponseInvestments;
import com.fastcampus.investment.dto.ResponseDto;
import com.fastcampus.investment.service.InvestmentsService;
import com.fastcampus.investment.service.ProductsService;

@RestController
@RequestMapping("/api")
public class Apis {
	@Autowired
	ProductsService productsService;
	@Autowired
	InvestmentsService investmentsService;

	@GetMapping("/product")
	public ResponseDto<List<Products>> productGet() {
		List<Products> list = productsService.findValidProducts();
		list.stream().forEach(products -> {
			products.setInvestedCount(investmentsService.findInvestorCount(products.getId()));
			products.setInvestedAmount(investmentsService.sumProductInvestments(products.getId()));
		});
		return new ResponseDto<>(list, HttpStatus.OK);
	}

	@PostMapping("/investment")
	public ResponseDto<ResponseInvestments> investmentPost(@RequestHeader("X-USER-ID") long userId,
		@ModelAttribute Investments investment) {
		long amount = investment.getInvestAmount();
		investment.setUserId(userId);
		if (investmentsService.checkAmountValidity(amount, investment.getProductId()) == false) {
			investment.setStatus(InvestmentStatus.FAIL);
		}
		Investments insertedInvestment= investmentsService.insertInvestment(investment);
		return new ResponseDto<>(new ResponseInvestments(insertedInvestment), HttpStatus.OK);
	}

	@GetMapping("/investment")
	public ResponseDto<List<ResponseInvestments>> investmentGet(@RequestHeader("X-USER-ID") long userId) {
		return new ResponseDto<>(investmentsService.findInvestByUserId(userId), HttpStatus.OK);
	}

	@PutMapping("/investment/{productId}")
	public ResponseDto<List<ResponseInvestments>> investmentPut(@RequestHeader("X-USER-ID") long userId,
		@PathVariable long productId) {
		List<ResponseInvestments> updatedInvests = investmentsService.cancelInvestStatus(userId, productId);
		return new ResponseDto<>(updatedInvests, HttpStatus.OK);
	}
}
