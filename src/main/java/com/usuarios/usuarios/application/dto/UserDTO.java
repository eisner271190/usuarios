package com.usuarios.usuarios.application.dto;

import java.util.Date;
import lombok.Data;

@Data
public class UserDTO
{
    private Long id;
    private String nombre;
    private String apellido;
    private String numero_documento;
    private String celular;
    private Date fecha_nacimiento;
    private String correo;
    private String clave;
    private RolDTO id_rol;
}
