package com.fastcampus.investment.domain;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 테스트 코드에서 제시한 변수명과 일치시키기 위한 반환용 클래스.
 */
@Getter @Setter
public class ResponseInvestments {
	private Long id;
	private Long userId;
	private Products product;
	private Date investedAt;
	private long investedAmount;
	private InvestmentStatus status;

	public ResponseInvestments(Investments invest) {
		id = invest.getId();
		userId = invest.getUserId();
		product = invest.getProduct();
		investedAt = invest.getInvestmentDate();
		status = invest.getStatus();
		this.investedAmount = invest.getInvestAmount();
	}

}
