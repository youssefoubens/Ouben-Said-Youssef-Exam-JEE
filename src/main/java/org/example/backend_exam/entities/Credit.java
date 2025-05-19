package org.example.backend_exam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "credit_type")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date requestDate;
    
    @Enumerated(EnumType.STRING)
    private CreditStatus status;
    
    @Temporal(TemporalType.DATE)
    private Date acceptanceDate;
    
    private Double amount;
    private Integer duration; // in months
    private Double interestRate;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    
    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Repayment> repayments = new ArrayList<>();
    
    public enum CreditStatus {
        IN_PROGRESS, ACCEPTED, REJECTED
    }
}