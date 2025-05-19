package org.example.backend_exam.repositories;

import org.example.backend_exam.entities.Credit;
import org.example.backend_exam.entities.Credit.CreditStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByClientId(Long clientId);
    List<Credit> findByStatus(CreditStatus status);
    List<Credit> findByRequestDateBetween(Date startDate, Date endDate);
    List<Credit> findByClientIdAndStatus(Long clientId, CreditStatus status);
}