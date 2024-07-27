package com.usuarios.usuarios.application.mapper;

import com.usuarios.usuarios.application.dto.RolDTO;
import com.usuarios.usuarios.domain.model.RolModel;
import com.usuarios.usuarios.infraestructure.jpa.entity.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRolMapper {
    RolModel toModel(RolDTO dto);
    RolDTO toEntity(RolModel model);
}
