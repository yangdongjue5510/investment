package com.fastcampus.investment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcampus.investment.domain.InvestmentStatus;
import com.fastcampus.investment.domain.Investments;

public interface InvestmentsRepository extends JpaRepository<Investments, Long> {

	default Investments updateInvestStatus(Investments invest, InvestmentStatus status) {
		invest.setStatus(status);
		save(invest);
		return invest;
	}

	List<Investments> findInvestmentsByIdAndStatusEquals(long productId, InvestmentStatus investmentStatus);

	List<Investments> findInvestmentsByUserId(long userId);

	Investments findInvestmentsByUserIdAndIdAndStatusEquals(long UserId, long productId, InvestmentStatus status);
}
