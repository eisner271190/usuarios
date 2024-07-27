package com.usuarios.usuarios.infraestructure.jpa.entity;

import com.usuarios.usuarios.domain.constants.UserConstants;
import java.util.Date;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{validation.name.required}")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "{validation.name.required}")
    @Column(nullable = false)
    private String apellido;

    @NotBlank(message = "{validation.document.required}")
    @Pattern(regexp = UserConstants.DOCUMENT_REGEX, message = "{validation.document.numeric}")
    @Column(nullable = false, unique = true)
    private String numero_documento;

    @NotBlank(message = "{validation.phone.required}")
    @Pattern(regexp = UserConstants.PHONE_REGEX, message = "{validation.phone.invalid}")
    @Column(nullable = false)
    private String celular;

    @Past(message = "{validation.date.past}")
    @DateTimeFormat(pattern = UserConstants.DATE_FORMAT)
    @Column(nullable = false)
    private Date fecha_nacimiento;

    @Email(message = "{validation.email.invalid}")
    @NotBlank(message = "{validation.email.required}")
    @Column(nullable = false, unique = true)
    private String correo;

    @NotBlank(message = "{validation.password.required}")
    @Column(nullable = false)
    private String clave;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private RolEntity id_rol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public RolEntity getId_rol() {
        return id_rol;
    }

    public void setId_rol(RolEntity id_rol) {
        this.id_rol = id_rol;
    }
}
