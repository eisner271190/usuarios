package com.usuarios.usuarios.application.mapper;

import com.usuarios.usuarios.application.dto.UserDTO;
import com.usuarios.usuarios.domain.model.UserModel;
import com.usuarios.usuarios.infraestructure.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    UserModel toModel(UserDTO dto);
    UserDTO toEntity(UserModel model);
}
