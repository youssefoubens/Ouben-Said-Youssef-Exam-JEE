package org.example.backend_exam.services;

import org.example.backend_exam.dtos.RepaymentDTO;
import java.util.List;

public interface RepaymentService {
    List<RepaymentDTO> getAllRepayments();
    RepaymentDTO getRepaymentById(Long id);
    RepaymentDTO saveRepayment(RepaymentDTO repaymentDTO);
    RepaymentDTO updateRepayment(Long id, RepaymentDTO repaymentDTO);
    void deleteRepayment(Long id);
    
    // Credit-related repayment operations
    List<RepaymentDTO> getRepaymentsByCreditId(Long creditId);
}