/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios.application.service;

import com.usuarios.usuarios.domain.constants.UserConstants;
import com.usuarios.usuarios.domain.model.UserModel;
import com.usuarios.usuarios.domain.repositories.IUserRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author usuario
 */
@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public List<UserModel> getUsers()
    {
        return userRepository.findAll();
    }
    
    public Optional<UserModel> getUser(Long id)
    {
        return userRepository.findById(id);
    }
    
    public boolean getOwnerById(Long id)
    {
        Optional<UserModel> user = userRepository.findById(id);
        
        if(user == null)
        {
            return false;
        }
        else
        {
            return UserConstants.ROLE_OWNER_ID.equals(user.get().getId_rol().getId());
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
