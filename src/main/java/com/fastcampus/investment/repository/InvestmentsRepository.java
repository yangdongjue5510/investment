package com.fastcampus.investment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcampus.investment.domain.Investments;

public interface InvestmentsRepository extends JpaRepository<Investments, Long> {
}
