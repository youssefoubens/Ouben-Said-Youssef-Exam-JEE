package org.example.backend_exam.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend_exam.entities.Credit.CreditStatus;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditSummaryDTO {
    private Long id;
    private String type;
    private Date requestDate;
    private CreditStatus status;
    private Double amount;
    private Integer duration;
}