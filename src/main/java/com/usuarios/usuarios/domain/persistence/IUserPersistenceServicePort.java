/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.usuarios.usuarios.domain.persistence;

import com.usuarios.usuarios.domain.model.UserModel;

/**
 *
 * @author usuario
 */
public interface IUserPersistenceServicePort
{
    UserModel findByCorreo(String email);
    void saveUser(UserModel user);
    UserModel findById(Long id);
}
