package com.fastcampus.investment.domain;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.tomcat.jni.Local;

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

	@ManyToOne(fetch = FetchType.EAGER )	//다대일 관계
	@JoinColumn(name="id")
	private Products product;

	@Transient	//product id
	private Long productId;

	@Column(nullable = false)
	private Long investAmount;

	@Temporal(TemporalType.DATE)
	private LocalDateTime investmentDate = LocalDateTime.now();	 //기본값을 현재로??

	@Enumerated(EnumType.STRING)
	@Column(name = "InvestmentStatus")
	private InvestmentStatus status = InvestmentStatus.INVESTED;		//기본값을 유효로 ?
}

