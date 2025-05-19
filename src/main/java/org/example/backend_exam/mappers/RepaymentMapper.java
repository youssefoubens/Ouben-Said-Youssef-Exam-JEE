package org.example.backend_exam.mappers;

import org.example.backend_exam.dtos.RepaymentDTO;
import org.example.backend_exam.entities.Repayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface RepaymentMapper {

    
    @Mapping(target = "creditId", source = "credit.id")
    RepaymentDTO toDto(Repayment repayment);
    
    @Mapping(target = "credit.id", source = "creditId")
    @Mapping(target = "credit.requestDate", ignore = true)
    @Mapping(target = "credit.status", ignore = true)
    @Mapping(target = "credit.acceptanceDate", ignore = true)
    @Mapping(target = "credit.amount", ignore = true)
    @Mapping(target = "credit.duration", ignore = true)
    @Mapping(target = "credit.interestRate", ignore = true)
    @Mapping(target = "credit.client", ignore = true)
    @Mapping(target = "credit.repayments", ignore = true)
    Repayment toEntity(RepaymentDTO repaymentDTO);
}