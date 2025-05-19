package org.example.backend_exam.services.impl;

import org.example.backend_exam.dtos.RepaymentDTO;
import org.example.backend_exam.entities.Credit;
import org.example.backend_exam.entities.Repayment;
import org.example.backend_exam.mappers.RepaymentMapper;
import org.example.backend_exam.repositories.CreditRepository;
import org.example.backend_exam.repositories.RepaymentRepository;
import org.example.backend_exam.services.RepaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RepaymentServiceImpl implements RepaymentService {

    private final RepaymentRepository repaymentRepository;
    private final CreditRepository creditRepository;
    private final RepaymentMapper repaymentMapper;

    public RepaymentServiceImpl(RepaymentRepository repaymentRepository,
                               CreditRepository creditRepository,
                               RepaymentMapper repaymentMapper) {
        this.repaymentRepository = repaymentRepository;
        this.creditRepository = creditRepository;
        this.repaymentMapper = repaymentMapper;
    }

    @Override
    public List<RepaymentDTO> getAllRepayments() {
        return repaymentRepository.findAll().stream()
                .map(repaymentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RepaymentDTO getRepaymentById(Long id) {
        Repayment repayment = repaymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repayment not found with id: " + id));
        return repaymentMapper.toDto(repayment);
    }

    @Override
    public RepaymentDTO saveRepayment(RepaymentDTO repaymentDTO) {
        // Verify credit exists
        Credit credit = creditRepository.findById(repaymentDTO.getCreditId())
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + repaymentDTO.getCreditId()));
        
        Repayment repayment = repaymentMapper.toEntity(repaymentDTO);
        repayment.setCredit(credit);
        Repayment savedRepayment = repaymentRepository.save(repayment);
        return repaymentMapper.toDto(savedRepayment);
    }

    @Override
    public RepaymentDTO updateRepayment(Long id, RepaymentDTO repaymentDTO) {
        // Check if repayment exists
        repaymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repayment not found with id: " + id));
        
        // Verify credit exists
        Credit credit = creditRepository.findById(repaymentDTO.getCreditId())
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + repaymentDTO.getCreditId()));
        
        // Set the ID to ensure we're updating the existing repayment
        repaymentDTO.setId(id);
        Repayment repayment = repaymentMapper.toEntity(repaymentDTO);
        repayment.setCredit(credit);
        Repayment updatedRepayment = repaymentRepository.save(repayment);
        return repaymentMapper.toDto(updatedRepayment);
    }

    @Override
    public void deleteRepayment(Long id) {
        repaymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Repayment not found with id: " + id));
        repaymentRepository.deleteById(id);
    }

    @Override
    public List<RepaymentDTO> getRepaymentsByCreditId(Long creditId) {
        // Verify credit exists
        creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + creditId));
        
        return repaymentRepository.findByCreditId(creditId).stream()
                .map(repaymentMapper::toDto)
                .collect(Collectors.toList());
    }
}