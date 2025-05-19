package org.example.backend_exam.services.impl;

import org.example.backend_exam.dtos.*;
import org.example.backend_exam.entities.*;
import org.example.backend_exam.mappers.CreditMapper;
import org.example.backend_exam.repositories.*;
import org.example.backend_exam.services.CreditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final PersonalCreditRepository personalCreditRepository;
    private final MortgageCreditRepository mortgageCreditRepository;
    private final ProfessionalCreditRepository professionalCreditRepository;
    private final ClientRepository clientRepository;
    private final CreditMapper creditMapper;

    public CreditServiceImpl(CreditRepository creditRepository,
                            PersonalCreditRepository personalCreditRepository,
                            MortgageCreditRepository mortgageCreditRepository,
                            ProfessionalCreditRepository professionalCreditRepository,
                            ClientRepository clientRepository,
                            CreditMapper creditMapper) {
        this.creditRepository = creditRepository;
        this.personalCreditRepository = personalCreditRepository;
        this.mortgageCreditRepository = mortgageCreditRepository;
        this.professionalCreditRepository = professionalCreditRepository;
        this.clientRepository = clientRepository;
        this.creditMapper = creditMapper;
    }

    @Override
    public List<CreditDTO> getAllCredits() {
        return creditRepository.findAll().stream()
                .map(creditMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CreditDTO getCreditById(Long id) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + id));
        return creditMapper.toDto(credit);
    }

    @Override
    public void deleteCredit(Long id) {
        creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + id));
        creditRepository.deleteById(id);
    }

    // Personal Credit Operations
    @Override
    public List<PersonalCreditDTO> getAllPersonalCredits() {
        return personalCreditRepository.findAll().stream()
                .map(creditMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PersonalCreditDTO getPersonalCreditById(Long id) {
        PersonalCredit personalCredit = personalCreditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal credit not found with id: " + id));
        return creditMapper.toDto(personalCredit);
    }

    @Override
    public PersonalCreditDTO savePersonalCredit(PersonalCreditDTO personalCreditDTO) {
        // Verify client exists
        Client client = clientRepository.findById(personalCreditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + personalCreditDTO.getClientId()));
        
        PersonalCredit personalCredit = creditMapper.toEntity(personalCreditDTO);
        personalCredit.setClient(client);
        PersonalCredit savedCredit = personalCreditRepository.save(personalCredit);
        return creditMapper.toDto(savedCredit);
    }

    @Override
    public PersonalCreditDTO updatePersonalCredit(Long id, PersonalCreditDTO personalCreditDTO) {
        // Check if credit exists
        personalCreditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal credit not found with id: " + id));
        
        // Verify client exists
        Client client = clientRepository.findById(personalCreditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + personalCreditDTO.getClientId()));
        
        // Set the ID to ensure we're updating the existing credit
        personalCreditDTO.setId(id);
        PersonalCredit personalCredit = creditMapper.toEntity(personalCreditDTO);
        personalCredit.setClient(client);
        PersonalCredit updatedCredit = personalCreditRepository.save(personalCredit);
        return creditMapper.toDto(updatedCredit);
    }

    // Mortgage Credit Operations
    @Override
    public List<MortgageCreditDTO> getAllMortgageCredits() {
        return mortgageCreditRepository.findAll().stream()
                .map(creditMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MortgageCreditDTO getMortgageCreditById(Long id) {
        MortgageCredit mortgageCredit = mortgageCreditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mortgage credit not found with id: " + id));
        return creditMapper.toDto(mortgageCredit);
    }

    @Override
    public MortgageCreditDTO saveMortgageCredit(MortgageCreditDTO mortgageCreditDTO) {
        // Verify client exists
        Client client = clientRepository.findById(mortgageCreditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + mortgageCreditDTO.getClientId()));
        
        MortgageCredit mortgageCredit = creditMapper.toEntity(mortgageCreditDTO);
        mortgageCredit.setClient(client);
        MortgageCredit savedCredit = mortgageCreditRepository.save(mortgageCredit);
        return creditMapper.toDto(savedCredit);
    }

    @Override
    public MortgageCreditDTO updateMortgageCredit(Long id, MortgageCreditDTO mortgageCreditDTO) {
        // Check if credit exists
        mortgageCreditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mortgage credit not found with id: " + id));
        
        // Verify client exists
        Client client = clientRepository.findById(mortgageCreditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + mortgageCreditDTO.getClientId()));
        
        // Set the ID to ensure we're updating the existing credit
        mortgageCreditDTO.setId(id);
        MortgageCredit mortgageCredit = creditMapper.toEntity(mortgageCreditDTO);
        mortgageCredit.setClient(client);
        MortgageCredit updatedCredit = mortgageCreditRepository.save(mortgageCredit);
        return creditMapper.toDto(updatedCredit);
    }

    // Professional Credit Operations
    @Override
    public List<ProfessionalCreditDTO> getAllProfessionalCredits() {
        return professionalCreditRepository.findAll().stream()
                .map(creditMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProfessionalCreditDTO getProfessionalCreditById(Long id) {
        ProfessionalCredit professionalCredit = professionalCreditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professional credit not found with id: " + id));
        return creditMapper.toDto(professionalCredit);
    }

    @Override
    public ProfessionalCreditDTO saveProfessionalCredit(ProfessionalCreditDTO professionalCreditDTO) {
        // Verify client exists
        Client client = clientRepository.findById(professionalCreditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + professionalCreditDTO.getClientId()));
        
        ProfessionalCredit professionalCredit = creditMapper.toEntity(professionalCreditDTO);
        professionalCredit.setClient(client);
        ProfessionalCredit savedCredit = professionalCreditRepository.save(professionalCredit);
        return creditMapper.toDto(savedCredit);
    }

    @Override
    public ProfessionalCreditDTO updateProfessionalCredit(Long id, ProfessionalCreditDTO professionalCreditDTO) {
        // Check if credit exists
        professionalCreditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professional credit not found with id: " + id));
        
        // Verify client exists
        Client client = clientRepository.findById(professionalCreditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + professionalCreditDTO.getClientId()));
        
        // Set the ID to ensure we're updating the existing credit
        professionalCreditDTO.setId(id);
        ProfessionalCredit professionalCredit = creditMapper.toEntity(professionalCreditDTO);
        professionalCredit.setClient(client);
        ProfessionalCredit updatedCredit = professionalCreditRepository.save(professionalCredit);
        return creditMapper.toDto(updatedCredit);
    }

    @Override
    public List<CreditSummaryDTO> getCreditsByClientId(Long clientId) {
        // Verify client exists
        clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
        
        return creditRepository.findByClientId(clientId).stream()
                .map(creditMapper::toCreditSummaryDto)
                .collect(Collectors.toList());
    }
}