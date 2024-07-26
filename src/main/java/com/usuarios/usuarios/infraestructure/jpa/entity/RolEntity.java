package com.usuarios.usuarios.infraestructure.jpa.entity;

import com.usuarios.usuarios.domain.model.*;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rol")

public class RolEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nombre;
	
	@Column
	private String descripcion;
	
	@OneToMany(mappedBy = "id_rol")
	private Set<UserEntity> usuarios;
}
