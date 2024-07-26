/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios.infraestructure.jpa.mapper;

import com.usuarios.usuarios.domain.model.RolModel;
import com.usuarios.usuarios.infraestructure.jpa.entity.RolEntity;

/**
 *
 * @author usuario
 */
//@Mapper(componentModel = "spring",
//        unmappedTargetPolicy = ReportingPolicy.IGNORE,
//        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public class RolEntityMapper {

    public static RolModel toModel(RolEntity rolEntity) {
        if (rolEntity == null) {
            return null;
        }

        RolModel rolModel = new RolModel();
        rolModel.setId(rolEntity.getId());
        rolModel.setNombre(rolEntity.getNombre());
        rolModel.setDescripcion(rolEntity.getDescripcion());

        return rolModel;
    }
    
    public static RolEntity toEntity(RolModel rolModel) {
        if (rolModel == null) {
            return null;
        }

        RolEntity rolEntity = new RolEntity();
        rolEntity.setId(rolModel.getId());
        rolEntity.setNombre(rolModel.getNombre());
        rolEntity.setDescripcion(rolModel.getDescripcion());

        return rolEntity;
    }
}
