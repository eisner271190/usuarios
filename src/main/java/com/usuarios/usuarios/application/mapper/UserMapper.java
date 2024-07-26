/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios.application.mapper;

import com.usuarios.usuarios.application.dto.UserDTO;
import com.usuarios.usuarios.domain.model.UserModel;

/**
 *
 * @author usuario
 */
public class UserMapper {

    public static UserModel toModel(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        UserModel userModel = new UserModel();
        userModel.setId(userDTO.getId());
        userModel.setNombre(userDTO.getNombre());
        userModel.setApellido(userDTO.getApellido());
        userModel.setNumero_documento(userDTO.getNumero_documento());
        userModel.setCelular(userDTO.getCelular());
        userModel.setFecha_nacimiento(userDTO.getFecha_nacimiento());
        userModel.setCorreo(userDTO.getCorreo());
        userModel.setClave(userDTO.getClave());
        userModel.setId_rol(RolMapper.toModel(userDTO.getId_rol()));

        return userModel;
    }
    
    public static UserDTO toDTO(UserModel userModel) {
        if (userModel == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userModel.getId());
        userDTO.setNombre(userModel.getNombre());
        userDTO.setApellido(userModel.getApellido());
        userDTO.setNumero_documento(userModel.getNumero_documento());
        userDTO.setCelular(userModel.getCelular());
        userDTO.setFecha_nacimiento(userModel.getFecha_nacimiento());
        userDTO.setCorreo(userModel.getCorreo());
        userDTO.setClave(userModel.getPassword());
        userDTO.setId_rol(RolMapper.toDTO(userModel.getId_rol()));

        return userDTO;
    }
}
