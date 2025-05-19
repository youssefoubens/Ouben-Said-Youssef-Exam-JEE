package org.example.backend_exam.repositories;

import org.example.backend_exam.entities.MortgageCredit;
import org.example.backend_exam.entities.MortgageCredit.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MortgageCreditRepository extends JpaRepository<MortgageCredit, Long> {
    List<MortgageCredit> findByPropertyType(PropertyType propertyType);
    List<MortgageCredit> findByClientId(Long clientId);
}