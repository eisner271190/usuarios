/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios.infraestructure.jpa.adapter;

import com.usuarios.usuarios.domain.model.RolModel;
import com.usuarios.usuarios.domain.persistence.IRolPersistenceServicePort;
import com.usuarios.usuarios.infraestructure.exception.RolNotFountException;
import com.usuarios.usuarios.infraestructure.jpa.mapper.IRolEntityMapper;
import com.usuarios.usuarios.infraestructure.jpa.repository.IRolRepository;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author usuario
 */
//@RequiredArgsConstructor
public class RolAdapter implements IRolPersistenceServicePort {

    private final IRolRepository rolRepository;
    private final IRolEntityMapper rolEntityMapper;

    public RolAdapter(IRolRepository rolRepository,
                      IRolEntityMapper rolEntityMapper) {
        this.rolRepository = rolRepository;
        this.rolEntityMapper = rolEntityMapper;
    }
    
    @Override
    public RolModel findByNombre(String nombre) {
        return rolEntityMapper.toModel(rolRepository.findByNombre(nombre)
                .orElseThrow(RolNotFountException::new));
    }
}
