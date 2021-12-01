package com.fastcampus.investment.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fastcampus.investment.domain.Products;
import com.fastcampus.investment.dto.ResponseDto;
import com.fastcampus.investment.service.InvestmentsService;
import com.fastcampus.investment.service.ProductsService;

@RestController
@RequestMapping("api")
public class Apis {
    // TODO: start
	@Autowired
	ProductsService productsService;
	@Autowired
	InvestmentsService investmentsService;

	@GetMapping("/product")
	public ResponseDto<List<Products>> productGet(@RequestHeader("X-USER-ID") long userId) {
		List<Products> list = productsService.findValidProducts();
		list.stream().forEach(products
			-> products.setInvestorCount(investmentsService.findInvestorCount(products.getId())));
		return new ResponseDto<>(list, HttpStatus.OK);
	}
}
