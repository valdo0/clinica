package com.clinica.clinica.laboratorio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "TIPO_ANALISIS")
public class TipoAnalisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @Column(nullable = false,unique = true)
    private String nombre;


    @NotBlank(message = "La descripción es obligatorio")
    @Size(max = 200, message = "La descripción no puede tener más de 200 caracteres")
    @Column(nullable = false)
    private String descripcion;

}
