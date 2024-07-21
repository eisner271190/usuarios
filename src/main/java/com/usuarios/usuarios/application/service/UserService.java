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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author usuario
 */
@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    private final IRolRepository rolRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public boolean getOwnerById(Long id)
    {
        Optional<UserModel> user = userRepository.findById(id);
        Optional<RolModel> rolAdmin = rolRepository.findByNombre(UserConstants.ROLE_OWNER);
        
        if(user == null)
        {
            return false;
        }
        else
        {
            return rolAdmin.get().getId().equals(user.get().getId_rol().getId());
        }
    }
    
    public void saveUser(UserModel user)
    {
        userRepository.save(user);
    }
    
    public void saveAccountOwner(UserModel user)
    {
        if(!isAdult(user.getFecha_nacimiento()))
        {
            throw new IllegalArgumentException("{validation.date.isadult}");
        }
        
        user.getId_rol().setId(UserConstants.ROLE_OWNER_ID);
        
        user.setClave(bCryptPasswordEncoder.encode(user.getClave()));
        
        userRepository.save(user);
    }
    
    public void deleteUser(Long id)
    {
        userRepository.deleteById(id);
    }
    
    private boolean isAdult(Date fechaNacimiento) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaNacimiento);
        int yearOfBirth = cal.get(Calendar.YEAR);
        
        cal.setTime(new Date());
        int currentYear = cal.get(Calendar.YEAR);
        
        return currentYear - yearOfBirth >= UserConstants.MINIMUM_AGE;
    }
}
