package com.clinica.clinica.laboratorio.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "LABORATORIO")
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @Column(nullable = false,unique = true)
    private String nombre;
    
    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 100, message = "La dirección no puede tener más de 100 caracteres")
    @Column(nullable = false)
    private String direccion;

    @Pattern(regexp = "\\+?[0-9]{9,15}", message = "El teléfono no es válido")
    @Column(length = 15)
    private String telefono;

    @NotNull
    private boolean habilitado = true;
    
    @ManyToMany
    @JoinTable(
        name = "LABORATORIO_TIPO_ANALISIS",
        joinColumns = @JoinColumn(name = "laboratorio_id"),
        inverseJoinColumns = @JoinColumn(name = "tipo_analisis_id"),
        uniqueConstraints = {
            @UniqueConstraint(
                name = "UK_LAB_TIPO_ANALISIS",
                columnNames = {"laboratorio_id", "tipo_analisis_id"}
            )
        }
    )
    private Set<TipoAnalisis> tiposAnalisis;
}
