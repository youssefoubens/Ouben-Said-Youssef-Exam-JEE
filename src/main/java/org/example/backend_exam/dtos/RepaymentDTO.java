package org.example.backend_exam.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend_exam.entities.Repayment.RepaymentType;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepaymentDTO {
    private Long id;
    private Date date;
    private Double amount;
    private RepaymentType type;
    private Long creditId;
}