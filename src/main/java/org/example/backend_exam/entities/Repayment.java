package org.example.backend_exam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date date;
    
    private Double amount;
    
    @Enumerated(EnumType.STRING)
    private RepaymentType type;
    
    @ManyToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;
    
    public enum RepaymentType {
        MONTHLY_PAYMENT, EARLY_REPAYMENT
    }
}