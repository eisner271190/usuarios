/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios.application.mapper;

import com.usuarios.usuarios.application.dto.RolDTO;
import com.usuarios.usuarios.domain.model.RolModel;

/**
 *
 * @author usuario
 */
public class RolMapper {

    public static RolModel toModel(RolDTO rolDTO) {
        if (rolDTO == null) {
            return null;
        }

        RolModel rolModel = new RolModel();
        rolModel.setId(rolDTO.getId());
        rolModel.setNombre(rolDTO.getNombre());
        rolModel.setDescripcion(rolDTO.getDescripcion());

        return rolModel;
    }
    
    public static RolDTO toDTO(RolModel rolModel) {
        if (rolModel == null) {
            return null;
        }

        RolDTO rolDTO = new RolDTO();
        rolDTO.setId(rolModel.getId());
        rolDTO.setNombre(rolModel.getNombre());
        rolDTO.setDescripcion(rolModel.getDescripcion());

        return rolDTO;
    }
}
