/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios.domain.usecase;

import com.usuarios.usuarios.domain.model.UserModel;
import com.usuarios.usuarios.domain.persistence.IUserPersistenceServicePort;
import com.usuarios.usuarios.domain.ports.IUserServicePort;

/**
 *
 * @author usuario
 */
public class UserUseCase implements IUserServicePort {

    private final IUserPersistenceServicePort userPersistenceServicePort;

    public UserUseCase(IUserPersistenceServicePort userPersistenceServicePort) {
        this.userPersistenceServicePort = userPersistenceServicePort;
    }
    
    @Override
    public UserModel findByCorreo(String email) {
        return userPersistenceServicePort.findByCorreo(email);
    }
    
    @Override
    public UserModel findById(Long id) {
        return userPersistenceServicePort.findById(id);
    }
    
    @Override
    public void saveUser(UserModel user){
        userPersistenceServicePort.saveUser(user);
    }
}
