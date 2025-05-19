package org.example.backend_exam.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend_exam.entities.MortgageCredit.PropertyType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MortgageCreditDTO extends CreditDTO {
    private PropertyType propertyType;
}