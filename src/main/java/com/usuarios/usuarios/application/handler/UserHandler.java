/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios.application.handler;

import com.usuarios.usuarios.application.dto.UserDTO;
import com.usuarios.usuarios.application.dto.UserOwnerResponse;
import com.usuarios.usuarios.application.mapper.UserMapper;
import com.usuarios.usuarios.domain.constants.UserConstants;
import com.usuarios.usuarios.domain.model.RolModel;
import com.usuarios.usuarios.domain.model.UserModel;
import com.usuarios.usuarios.domain.ports.IRolServicePort;
import com.usuarios.usuarios.domain.ports.IUserServicePort;
import java.util.Calendar;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author usuario
 */
@Service
@RequiredArgsConstructor
public class UserHandler implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final UserMapper userMapper;
    private final IRolServicePort rolServicePort;
    private final PasswordEncoder bCryptPasswordEncoder;
    
    @Override
    public UserOwnerResponse getOwnerById(Long id)
    {
        UserModel user = userServicePort.findById(id);
        
        UserOwnerResponse response = new UserOwnerResponse();
        
        response.setValue(validateRol(user.getId_rol().getId(), UserConstants.ROLE_OWNER));
        
        return response;
    }
    
    @Override
    public void saveAccountOwner(UserDTO user)
    {
        if(!isAdult(user.getFecha_nacimiento())) throw new IllegalArgumentException("{validation.date.isadult}");
        
        saveAccount(user, UserConstants.ROLE_OWNER);
    }
    
    @Override
    public void saveAccountEmployee(UserDTO user)
    {
        saveAccount(user, UserConstants.ROLE_EMPLOYEE);
    }
    
    @Override
    public void saveAccountClient(UserDTO user)
    {
        saveAccount(user, UserConstants.ROLE_CLIENT);
    }
    
    @Override
    public void saveAccountAdmin(UserDTO user)
    {
        saveAccount(user, UserConstants.ROLE_ADMIN);
    }
    
    @Override
    public void saveAccount(UserDTO userDTO, String roleName)
    {
        UserModel user = userMapper.toModel(userDTO);
        RolModel role = rolServicePort.findByNombre(roleName);
        
        user.setId_rol(role);
        
        user.setClave(bCryptPasswordEncoder.encode(user.getClave()));
        
        userServicePort.saveUser(user);
    }
    
    @Override
    public boolean validateRol(Long idRol, String rolName)
    {
        RolModel role = rolServicePort.findByNombre(rolName);
        
        return role == null ? false : role.getId().equals(idRol);
    }
    
    private boolean isAdult(Date fechaNacimiento)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaNacimiento);
        int yearOfBirth = cal.get(Calendar.YEAR);
        
        cal.setTime(new Date());
        int currentYear = cal.get(Calendar.YEAR);
        
        return currentYear - yearOfBirth >= UserConstants.MINIMUM_AGE;
    }
}
