package com.clinica.clinica.analisis.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnalisisRequestDTO {

    @NotNull(message = "El laboratorio es obligatorio")
    private Long laboratorioId;

    @NotNull(message = "El tipo de análisis es obligatorio")
    private Long tipoAnalisisId;

    @NotNull(message = "El usuario (paciente) es obligatorio")
    private Long usuarioId;

    private String descripcion;

    private String comentarios;
}
