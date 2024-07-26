/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios.domain.usecase;

import com.usuarios.usuarios.domain.model.RolModel;
import com.usuarios.usuarios.domain.persistence.IRolPersistenceServicePort;
import com.usuarios.usuarios.domain.ports.IRolServicePort;

/**
 *
 * @author usuario
 */
public class RolUseCase implements IRolServicePort {

    private final IRolPersistenceServicePort rolPersistenceServicePort;

    public RolUseCase(IRolPersistenceServicePort rolPersistenceServicePort) {
        this.rolPersistenceServicePort = rolPersistenceServicePort;
    }

    @Override
    public RolModel findByNombre(String nombre) {
        return rolPersistenceServicePort.findByNombre(nombre);
    }
}
