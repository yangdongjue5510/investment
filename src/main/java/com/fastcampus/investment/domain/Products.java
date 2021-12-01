package com.fastcampus.investment.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String title;
	private Long totalInvestingAmount;
	@Temporal(TemporalType.DATE)
	private Date startedAt;
	@Temporal(TemporalType.DATE)
	private Date finishedAt;

}
