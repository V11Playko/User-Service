package com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.request;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.CreateOwnerRequestDto;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICreateOwnerRequestMapper {
    User toUser(CreateOwnerRequestDto createOwnerRequestDto);
}
