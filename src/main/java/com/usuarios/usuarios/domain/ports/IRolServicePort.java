/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.usuarios.usuarios.domain.ports;

import com.usuarios.usuarios.domain.model.RolModel;

/**
 *
 * @author usuario
 */
public interface IRolServicePort
{
    RolModel findByNombre(String nombre);
}
