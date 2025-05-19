package org.example.backend_exam.repositories;

import org.example.backend_exam.entities.Repayment;
import org.example.backend_exam.entities.Repayment.RepaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
    List<Repayment> findByCreditId(Long creditId);
    List<Repayment> findByType(RepaymentType type);
    List<Repayment> findByDateBetween(Date startDate, Date endDate);
    List<Repayment> findByCreditIdAndType(Long creditId, RepaymentType type);
}