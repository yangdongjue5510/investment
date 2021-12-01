package com.fastcampus.investment.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Investments {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long investId;
	@NotNull
	private Long userId;

	@ManyToOne	//다대일 관계
	@JoinColumn(name="id")
	@NotNull
	private Products product;

	@NotNull
	private Long investmentAmount;

	private Date investmentDate = new Date();	 //기본값을 현재로??

	@Enumerated(EnumType.STRING)
	private InvestmentStatus investmentStatus = InvestmentStatus.VALID;		//기본값을 유효로 ?
}
