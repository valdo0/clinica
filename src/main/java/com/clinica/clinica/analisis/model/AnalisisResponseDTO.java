package com.clinica.clinica.analisis.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AnalisisResponseDTO {
    private Long id;
    private String laboratorioNombre;
    private String tipoAnalisisNombre;
    private String usuarioNombre;
    private String usuarioEmail;
    private EstadoAnalisis estado;
    private String descripcion;
    private String comentarios;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaFinalizacion;
}
