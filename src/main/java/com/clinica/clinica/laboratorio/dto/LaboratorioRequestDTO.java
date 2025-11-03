package com.clinica.clinica.laboratorio.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LaboratorioRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 100, message = "La dirección no puede tener más de 100 caracteres")
    private String direccion;

    @Pattern(regexp = "\\+?[0-9]{9,15}", message = "El teléfono no es válido")
    private String telefono;

    @NotNull(message = "El estado es obligatorio")
    private Boolean habilitado = true;

    private Set<Long> tiposAnalisisIds;

}
