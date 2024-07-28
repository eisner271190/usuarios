/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios.infraestructure.jpa.adapter;

import com.usuarios.usuarios.domain.model.UserModel;
import com.usuarios.usuarios.domain.persistence.IUserPersistenceServicePort;
import com.usuarios.usuarios.infraestructure.jpa.entity.UserEntity;
import com.usuarios.usuarios.infraestructure.jpa.mapper.IUserEntityMapper;
import com.usuarios.usuarios.infraestructure.jpa.repository.IUserRepository;
import com.usuarios.usuarios.infraestructure.exception.UserNotFoundException;

import java.util.Optional;

/**
 *
 * @author usuario
 */
//@RequiredArgsConstructor
public class UserAdapter implements IUserPersistenceServicePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    public UserAdapter(IUserRepository userRepository,
                       IUserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public UserModel findByCorreo(String email) {
        return userEntityMapper.toModel(userRepository.findByCorreo(email)
                .orElseThrow(UserNotFoundException::new));
    }
    
    @Override
    public void saveUser(UserModel user) {
        //TODO: if(userRepository.findByCorreo(user.getCorreo()).isPresent()) throw new UserAlreadyExistsException();
        
        userRepository.save(userEntityMapper.toEntity(user));
    }
    
    @Override
    public UserModel findById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        return userEntityMapper.toModel(userEntity.orElseThrow(UserNotFoundException::new));
    }
}
