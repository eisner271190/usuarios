/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios.infraestructure.jpa.adapter;

import com.usuarios.usuarios.domain.model.UserModel;
import com.usuarios.usuarios.domain.persistence.IUserPersistenceServicePort;
import com.usuarios.usuarios.infraestructure.jpa.mapper.UserEntityMapper;
import com.usuarios.usuarios.infraestructure.jpa.repository.IUserRepository;
import com.usuarios.usuarios.infraestructure.exception.UserNotFountException;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author usuario
 */
@RequiredArgsConstructor
public class UserAdapter implements IUserPersistenceServicePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    
    @Override
    public UserModel findByCorreo(String email) {
        return userEntityMapper.toModel(userRepository.findByCorreo(email)
                .orElseThrow(UserNotFountException::new));
    }
    
    @Override
    public void saveUser(UserModel user) {
        //if(userRepository.findByCorreo(user.getCorreo()).isPresent()) throw new UserAlreadyExistsException();
        
        userRepository.save(userEntityMapper.toEntity(user));
    }
    
    @Override
    public UserModel findById(Long id) {
        return userEntityMapper.toModel(userRepository.findById(id)
                .orElseThrow(UserNotFountException::new));
    }
}
