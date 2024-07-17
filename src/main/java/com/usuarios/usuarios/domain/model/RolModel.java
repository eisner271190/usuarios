package com.usuarios.usuarios.domain.model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rol")

public class RolModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nombre;
	
	@Column
	private String descripcion;
	
	@OneToMany(mappedBy = "id_rol")
	private Set<UserModel> usuarios;
}
