package com.usuarios.usuarios.infraestructure.jpa.mapper;

import com.usuarios.usuarios.domain.model.RolModel;
import com.usuarios.usuarios.infraestructure.jpa.entity.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRolEntityMapper {
    RolModel toModel(RolEntity entity);
    RolEntity toEntity(RolModel model);
}
