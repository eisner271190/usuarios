/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios.infraestructure.controllers;

import com.usuarios.usuarios.application.service.UserService;
import com.usuarios.usuarios.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {
    
    private final UserService userService;
    
    @GetMapping("/owner/{id}")
    public boolean getOwnerById(@PathVariable Long id)
    {
        return userService.getOwnerById(id);
    }
    
    @PostMapping("/admin/saveAccountOwner")
    public void saveAccountOwner(@RequestBody UserModel user)
    {
        userService.saveAccountOwner(user);
    }
}
