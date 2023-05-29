package com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.request;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.CreateClientRequestDto;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICreateClientRequestMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "dniNumber", source = "dniNumber")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    User toUser(CreateClientRequestDto createClientRequestDto);
}
