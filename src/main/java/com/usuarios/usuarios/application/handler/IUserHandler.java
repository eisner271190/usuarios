/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios.application.handler;

import com.usuarios.usuarios.application.dto.UserDTO;
import com.usuarios.usuarios.application.dto.UserOwnerResponse;

/**
 *
 * @author usuario
 */
public interface IUserHandler {
    UserOwnerResponse getOwnerById(Long id);
    void saveAccountOwner(UserDTO user);
    void saveAccountEmployee(UserDTO user);
    void saveAccountClient(UserDTO user);
    void saveAccountAdmin(UserDTO user);
    void saveAccount(UserDTO user, String roleName);
    boolean validateRol(Long idRol, String rolName);
}
