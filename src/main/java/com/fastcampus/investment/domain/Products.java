package com.fastcampus.investment.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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

	@Column(name = "TOTAL_INVESTING_AMOUNT")
	private Long totalInvestingAmount;

	@Temporal(TemporalType.DATE)
	@Column(name = "STARTED_AT")
	private Date startedAt;

	@Temporal(TemporalType.DATE)
	@Column(name= "FINISHED_AT")
	private Date finishedAt;

	@Transient	//JPA로 활용되지 않음.
	private long investorCount;
}
