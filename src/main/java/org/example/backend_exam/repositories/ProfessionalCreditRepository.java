package org.example.backend_exam.repositories;

import org.example.backend_exam.entities.ProfessionalCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionalCreditRepository extends JpaRepository<ProfessionalCredit, Long> {
    List<ProfessionalCredit> findByPurpose(String purpose);
    List<ProfessionalCredit> findByCompanyName(String companyName);
    List<ProfessionalCredit> findByClientId(Long clientId);
}