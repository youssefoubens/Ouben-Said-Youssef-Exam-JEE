package org.example.backend_exam.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend_exam.entities.Credit.CreditStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditDTO {
    private Long id;
    private Date requestDate;
    private CreditStatus status;
    private Date acceptanceDate;
    private Double amount;
    private Integer duration;
    private Double interestRate;
    private Long clientId;
    private String clientName;
    private List<RepaymentDTO> repayments = new ArrayList<>();

    // Type of credit (for polymorphic handling)
    private String creditType;
}
