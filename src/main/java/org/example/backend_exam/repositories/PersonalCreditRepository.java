package org.example.backend_exam.repositories;

import org.example.backend_exam.entities.PersonalCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalCreditRepository extends JpaRepository<PersonalCredit, Long> {
    List<PersonalCredit> findByPurpose(String purpose);
    List<PersonalCredit> findByClientId(Long clientId);
}