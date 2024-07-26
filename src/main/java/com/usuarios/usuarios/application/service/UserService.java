///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.usuarios.usuarios.application.service;
//
//import com.usuarios.usuarios.domain.constants.UserConstants;
//import com.usuarios.usuarios.domain.model.RolModel;
//import com.usuarios.usuarios.domain.model.UserModel;
//import com.usuarios.usuarios.domain.repositories.IRolRepository;
//import com.usuarios.usuarios.domain.repositories.IUserRepository;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Optional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// *
// * @author usuario
// */
//@Service
//@RequiredArgsConstructor
//public class UserService {
//    private final IUserRepository userRepository;
//    private final IRolRepository rolRepository;
//    private final PasswordEncoder bCryptPasswordEncoder;
//    
//    public boolean getOwnerById(Long id)
//    {
//        Optional<UserModel> user = userRepository.findById(id);
//        
//        if(user.isEmpty())return false;
//        
//        return validateRol(user.get().getId_rol().getId(), UserConstants.ROLE_OWNER);
//    }
//    
//    public void saveAccountOwner(UserModel user) throws Exception
//    {
//        if(!isAdult(user.getFecha_nacimiento())) throw new IllegalArgumentException("{validation.date.isadult}");
//        
//        saveAccount(user, UserConstants.ROLE_OWNER);
//    }
//    
//    public void saveAccountEmployee(UserModel user) throws Exception
//    {
//        saveAccount(user, UserConstants.ROLE_EMPLOYEE);
//    }
//    
//    public void saveAccountClient(UserModel user) throws Exception
//    {
//        saveAccount(user, UserConstants.ROLE_CLIENT);
//    }
//    
//    public void saveAccountAdmin(UserModel user) throws Exception
//    {
//        saveAccount(user, UserConstants.ROLE_ADMIN);
//    }
//    
//    public void saveAccount(UserModel user, String roleName) throws Exception
//    {
//        Optional<RolModel> role = rolRepository.findByNombre(roleName);
//        
//        if(role.isEmpty()) throw new Exception("{validation.saveAccount.roleNotExist}");
//        
//        user.setId_rol(role.get());
//        
//        user.setClave(bCryptPasswordEncoder.encode(user.getClave()));
//        
//        userRepository.save(user);
//    }
//    
//    private boolean isAdult(Date fechaNacimiento)
//    {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(fechaNacimiento);
//        int yearOfBirth = cal.get(Calendar.YEAR);
//        
//        cal.setTime(new Date());
//        int currentYear = cal.get(Calendar.YEAR);
//        
//        return currentYear - yearOfBirth >= UserConstants.MINIMUM_AGE;
//    }
//    
//    public boolean validateRol(Long idRol, String rolName)
//    {
//        Optional<RolModel> role = rolRepository.findByNombre(rolName);
//        
//        return role.isEmpty() ? false : role.get().getId().equals(idRol);
//    }
//}
