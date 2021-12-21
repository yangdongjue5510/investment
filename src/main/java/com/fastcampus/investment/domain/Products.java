package com.fastcampus.investment.domain;

import java.time.LocalDateTime;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;

	@Column(name = "TOTAL_INVESTING_AMOUNT")
	private Long totalInvestAmount;

	/**
	 * investedCount, investedAmount는 db, jpa에 활용되지 않음.
	 */
	@Transient
	private long investedCount;

	@Transient
	private long investedAmount;

	@Temporal(TemporalType.DATE)
	@Column(name = "STARTED_AT")
	private Date startedAt;

	@Temporal(TemporalType.DATE)
	@Column(name= "FINISHED_AT")
	private Date finishedAt;

}
