package org.example.backend_exam.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("PERSONAL")
public class PersonalCredit extends Credit {
    private String purpose; // e.g., car purchase, education, renovations
}