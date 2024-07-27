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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para apellido
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Getter y Setter para numero_documento
    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    // Getter y Setter para celular
    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    // Getter y Setter para fecha_nacimiento
    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    // Getter y Setter para correo
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Getter y Setter para clave
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    // Getter y Setter para id_rol
    public RolDTO getId_rol() {
        return id_rol;
    }

    public void setId_rol(RolDTO id_rol) {
        this.id_rol = id_rol;
    }
}
