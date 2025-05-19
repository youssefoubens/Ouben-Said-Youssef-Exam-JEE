package org.example.backend_exam.services;

import org.example.backend_exam.dtos.*;
import java.util.List;

public interface CreditService {
    // Generic credit operations
    List<CreditDTO> getAllCredits();
    CreditDTO getCreditById(Long id);
    void deleteCredit(Long id);
    
    // Personal credit operations
    List<PersonalCreditDTO> getAllPersonalCredits();
    PersonalCreditDTO getPersonalCreditById(Long id);
    PersonalCreditDTO savePersonalCredit(PersonalCreditDTO personalCreditDTO);
    PersonalCreditDTO updatePersonalCredit(Long id, PersonalCreditDTO personalCreditDTO);
    
    // Mortgage credit operations
    List<MortgageCreditDTO> getAllMortgageCredits();
    MortgageCreditDTO getMortgageCreditById(Long id);
    MortgageCreditDTO saveMortgageCredit(MortgageCreditDTO mortgageCreditDTO);
    MortgageCreditDTO updateMortgageCredit(Long id, MortgageCreditDTO mortgageCreditDTO);
    
    // Professional credit operations
    List<ProfessionalCreditDTO> getAllProfessionalCredits();
    ProfessionalCreditDTO getProfessionalCreditById(Long id);
    ProfessionalCreditDTO saveProfessionalCredit(ProfessionalCreditDTO professionalCreditDTO);
    ProfessionalCreditDTO updateProfessionalCredit(Long id, ProfessionalCreditDTO professionalCreditDTO);
    
    // Client-related credit operations
    List<CreditSummaryDTO> getCreditsByClientId(Long clientId);
}