package org.example.backend_exam.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("MORTGAGE")
public class MortgageCredit extends Credit {
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;
    
    public enum PropertyType {
        APARTMENT, HOUSE, COMMERCIAL_PROPERTY
    }
}