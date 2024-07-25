package com.usuarios.usuarios.domain.model;

import com.usuarios.usuarios.domain.constants.UserConstants;
import java.util.Date;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@Table(name = "user")
public class UserModel implements UserDetails {
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
    private RolModel id_rol;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority((this.getId_rol().getNombre())));
    }
    @Override
    public boolean isAccountNonExpired() {
       return true;
    }
    @Override
    public boolean isAccountNonLocked() {
       return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return this.getClave();
    }

    @Override
    public String getUsername() {
        return this.getCorreo();
    }
}
