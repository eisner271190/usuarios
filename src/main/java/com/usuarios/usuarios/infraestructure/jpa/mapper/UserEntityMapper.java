/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios.infraestructure.jpa.mapper;

import com.usuarios.usuarios.domain.model.UserModel;
import com.usuarios.usuarios.infraestructure.jpa.entity.UserEntity;

/**
 *
 * @author usuario
 */
public class UserEntityMapper {

    public static UserModel toModel(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setNombre(userEntity.getNombre());
        userModel.setApellido(userEntity.getApellido());
        userModel.setNumero_documento(userEntity.getNumero_documento());
        userModel.setCelular(userEntity.getCelular());
        userModel.setFecha_nacimiento(userEntity.getFecha_nacimiento());
        userModel.setCorreo(userEntity.getCorreo());
        userModel.setClave(userEntity.getClave());
        userModel.setId_rol(RolEntityMapper.toModel(userEntity.getId_rol()));

        return userModel;
    }
    
    public static UserEntity toEntity(UserModel userModel) {
        if (userModel == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userModel.getId());
        userEntity.setNombre(userModel.getNombre());
        userEntity.setApellido(userModel.getApellido());
        userEntity.setNumero_documento(userModel.getNumero_documento());
        userEntity.setCelular(userModel.getCelular());
        userEntity.setFecha_nacimiento(userModel.getFecha_nacimiento());
        userEntity.setCorreo(userModel.getCorreo());
        userEntity.setClave(userModel.getPassword());
        userEntity.setId_rol(RolEntityMapper.toEntity(userModel.getId_rol()));

        return userEntity;
    }
}
