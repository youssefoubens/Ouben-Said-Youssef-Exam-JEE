package org.example.backend_exam.mappers;

import org.example.backend_exam.dtos.*;
import org.example.backend_exam.entities.*;
import org.hibernate.annotations.Comments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {RepaymentMapper.class})
public interface CreditMapper {


    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "creditType", expression = "java(getCreditType(credit))")
    CreditDTO toDto(Credit credit);

    @Mapping(target = "client.id", source = "clientId")
    @Mapping(target = "client.name", ignore = true)
    @Mapping(target = "client.email", ignore = true)
    @Mapping(target = "client.credits", ignore = true)
    Credit toEntity(CreditDTO creditDTO);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "creditType", constant = "PERSONAL")
    PersonalCreditDTO toDto(PersonalCredit personalCredit);

    @Mapping(target = "client.id", source = "clientId")
    @Mapping(target = "client.name", ignore = true)
    @Mapping(target = "client.email", ignore = true)
    @Mapping(target = "client.credits", ignore = true)
    PersonalCredit toEntity(PersonalCreditDTO personalCreditDTO);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "creditType", constant = "MORTGAGE")
    MortgageCreditDTO toDto(MortgageCredit mortgageCredit);

    @Mapping(target = "client.id", source = "clientId")
    @Mapping(target = "client.name", ignore = true)
    @Mapping(target = "client.email", ignore = true)
    @Mapping(target = "client.credits", ignore = true)
    MortgageCredit toEntity(MortgageCreditDTO mortgageCreditDTO);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "creditType", constant = "PROFESSIONAL")
    ProfessionalCreditDTO toDto(ProfessionalCredit professionalCredit);

    @Mapping(target = "client.id", source = "clientId")
    @Mapping(target = "client.name", ignore = true)
    @Mapping(target = "client.email", ignore = true)
    @Mapping(target = "client.credits", ignore = true)
    ProfessionalCredit toEntity(ProfessionalCreditDTO professionalCreditDTO);

    @Mapping(target = "type", expression = "java(getCreditType(credit))")
    CreditSummaryDTO toCreditSummaryDto(Credit credit);

    default String getCreditType(Credit credit) {
        if (credit instanceof PersonalCredit) {
            return "PERSONAL";
        } else if (credit instanceof MortgageCredit) {
            return "MORTGAGE";
        } else if (credit instanceof ProfessionalCredit) {
            return "PROFESSIONAL";
        }
        return "UNKNOWN";
    }
}
