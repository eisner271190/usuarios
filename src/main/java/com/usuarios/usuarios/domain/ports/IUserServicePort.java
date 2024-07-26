/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.usuarios.usuarios.domain.ports;

import com.usuarios.usuarios.domain.model.UserModel;

/**
 *
 * @author usuario
 */
public interface IUserServicePort
{
    UserModel findById(Long id);
    UserModel findByCorreo(String email);
    void saveUser(UserModel user);
}
