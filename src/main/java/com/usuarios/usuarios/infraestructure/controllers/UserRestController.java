/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios.infraestructure.controllers;

import com.usuarios.usuarios.application.dto.UserDTO;
import com.usuarios.usuarios.application.dto.UserOwnerResponse;
import com.usuarios.usuarios.application.handler.IUserHandler;
import com.usuarios.usuarios.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author usuario
 */
@RestController
@RequestMapping(path = "api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    
    private final IUserHandler userHandler;
    
    @GetMapping("/owner/{id}")
    public ResponseEntity<UserOwnerResponse> getOwnerById(@PathVariable Long id)
    {
        return ResponseEntity.ok(userHandler.getOwnerById(id));
    }

    @PostMapping("/admin/saveAccountOwner")
    public ResponseEntity<Void> saveAccountOwner(@RequestBody UserDTO user)
    {
        userHandler.saveAccountOwner(user);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/owner/saveAccountEmployee")
    public ResponseEntity<Void> saveAccountEmployee(@RequestBody UserDTO user)
    {
        userHandler.saveAccountEmployee(user);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/client/saveAccountClient")
    public void saveAccountClient(@RequestBody UserDTO user)
    {
        userHandler.saveAccountClient(user);
    }
    
    @PostMapping("/auth/saveAccountAdmin")
    public void saveAccountAdmin(@RequestBody UserDTO user)
    {
        userHandler.saveAccountAdmin(user);
    }
}
