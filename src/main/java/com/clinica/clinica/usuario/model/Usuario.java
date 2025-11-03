package com.clinica.clinica.usuario.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "USUARIO",uniqueConstraints = {
    @UniqueConstraint(name = "UK_USUARIO_EMAIL",columnNames = "EMAIL"),        
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Size(max = 100, message = "El email no puede tener más de 100 caracteres")
    @Column(nullable = false)
    private String email;

    @Pattern(regexp = "\\+?[0-9]{9,15}", message = "El teléfono no es válido")
    @Column(length = 15)
    private String telefono;

    @PastOrPresent(message = "La fecha de registro no puede ser futura")
    @Column(name = "FECHA_REGISTRO", nullable = false)
    private LocalDate fechaRegistro = LocalDate.now();

    @NotBlank(message = "El rol es obligatorio")
    @Pattern(regexp = "ADMIN|USER", message = "El rol debe ser ADMIN o USER")
    @Column(nullable = false, length = 10)
    private String rol;
    
}
