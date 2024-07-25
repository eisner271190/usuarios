/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios.application.service;

import com.usuarios.usuarios.domain.constants.UserConstants;
import com.usuarios.usuarios.domain.model.RolModel;
import com.usuarios.usuarios.domain.model.UserModel;
import com.usuarios.usuarios.domain.repositories.IRolRepository;
import com.usuarios.usuarios.domain.repositories.IUserRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author usuario
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;
    private final IRolRepository rolRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    
    public boolean getOwnerById(Long id)
    {
        Optional<UserModel> user = userRepository.findById(id);
        
        if(user == null)
        {
            return false;
        }
        else
        {
            return validateRol(user.get().getId_rol().getId(), UserConstants.ROLE_OWNER);
        }
    }
    
    public void saveAccountOwner(UserModel user)
    {
        Optional<RolModel> role = rolRepository.findByNombre(UserConstants.ROLE_OWNER);
        
        if(!isAdult(user.getFecha_nacimiento()))
        {
            throw new IllegalArgumentException("{validation.date.isadult}");
        }
        
        user.getId_rol().setId(role.get().getId());
        
        user.setClave(bCryptPasswordEncoder.encode(user.getClave()));
        
        userRepository.save(user);
    }
    
    public void saveAccountEmployee(UserModel user)
    {
        Optional<RolModel> role = rolRepository.findByNombre(UserConstants.ROLE_EMPLOYEE);
        
        user.getId_rol().setId(role.get().getId());
        
        user.setClave(bCryptPasswordEncoder.encode(user.getClave()));
        
        userRepository.save(user);
    }
    
    private boolean isAdult(Date fechaNacimiento) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaNacimiento);
        int yearOfBirth = cal.get(Calendar.YEAR);
        
        cal.setTime(new Date());
        int currentYear = cal.get(Calendar.YEAR);
        
        return currentYear - yearOfBirth >= UserConstants.MINIMUM_AGE;
    }
    
    private boolean validateRol(Long idRol, String rolName)
    {
        Optional<RolModel> role = rolRepository.findByNombre(rolName);
        return role == null ? false : role.get().getId().equals(idRol);
    }
}
