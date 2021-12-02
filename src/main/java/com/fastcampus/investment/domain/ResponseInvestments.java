package com.fastcampus.investment.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

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
