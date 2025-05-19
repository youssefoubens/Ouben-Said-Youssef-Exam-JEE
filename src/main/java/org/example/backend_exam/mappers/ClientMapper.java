package org.example.backend_exam.mappers;

import org.example.backend_exam.dtos.ClientDTO;
import org.example.backend_exam.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {CreditMapper.class})
public interface ClientMapper {

    
    @Mapping(target = "credits", source = "credits")
    ClientDTO toDto(Client client);
    
    @Mapping(target = "credits", ignore = true)
    Client toEntity(ClientDTO clientDTO);
}