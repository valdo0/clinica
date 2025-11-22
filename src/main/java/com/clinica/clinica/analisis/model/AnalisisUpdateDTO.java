package com.clinica.clinica.analisis.model;

import lombok.Data;

@Data
public class AnalisisUpdateDTO {
    private Long laboratorioId;
    private Long tipoAnalisisId;
    private EstadoAnalisis estado;
    private String descripcion;
    private String comentarios;
}
