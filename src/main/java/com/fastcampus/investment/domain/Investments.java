package com.fastcampus.investment.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Getter @Setter
public class Investments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "investId", nullable = false)
	private Long id;

	@Column(nullable = false)
	private Long userId;

	@ManyToOne	//다대일 관계
	@JoinColumn(name="id")
	private Products product;

	@Transient	//product id
	private Long productId;

	@Column(nullable = false)
	private Long investAmount;

	@Temporal(TemporalType.DATE)
	private Date investmentDate = new Date();	 //기본값을 현재로??

	@Enumerated(EnumType.STRING)
	@Column(name = "InvestmentStatus")
	private InvestmentStatus status = InvestmentStatus.INVESTED;		//기본값을 유효로 ?
}

