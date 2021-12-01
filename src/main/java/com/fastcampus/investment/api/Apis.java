package com.fastcampus.investment.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class Apis {
    // TODO: start

	@GetMapping("/product")
	public String productGet() {

		return null;
	}
}
