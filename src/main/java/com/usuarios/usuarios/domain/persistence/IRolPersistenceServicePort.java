/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.usuarios.usuarios.domain.persistence;

import com.usuarios.usuarios.domain.model.RolModel;

/**
 *
 * @author usuario
 */
public interface IRolPersistenceServicePort
{
    RolModel findByNombre(String nombre);
}
