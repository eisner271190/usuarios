package com.usuarios.usuarios.infraestructure.jpa.mapper;

import com.usuarios.usuarios.domain.model.UserModel;
import com.usuarios.usuarios.infraestructure.jpa.entity.UserEntity;
import com.usuarios.usuarios.infraestructure.security.UserDetailsSecurity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {
    @Mapping(source = "id_rol", target = "id_rol")
    UserModel toModel(UserEntity user);

    @Mapping(source = "id_rol", target = "id_rol")
    UserEntity toEntity(UserModel user);

    UserDetailsSecurity toUserDetails(UserEntity user);
}
