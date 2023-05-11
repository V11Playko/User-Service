package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {
    @Mapping(target = "roleEntity.id", source = "role.id")
    UserEntity toEntity(User user);
    @Mapping(target = "role.id", source = "roleEntity.id")
    User toUser(UserEntity userEntity);
    List<User> toUserList(List<UserEntity> userEntityList);
}
