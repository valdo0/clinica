package com.clinica.clinica.analisis.model;

import java.time.LocalDateTime;

import com.clinica.clinica.laboratorio.model.Laboratorio;
import com.clinica.clinica.laboratorio.model.TipoAnalisis;
import com.clinica.clinica.usuario.model.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ANALISIS")
public class Analisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "LABORATORIO_ID", nullable = false)
    private Laboratorio laboratorio;

    @ManyToOne
    @JoinColumn(name = "TIPO_ANALISIS_ID", nullable = false)
    private TipoAnalisis tipoAnalisis;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario; // Paciente

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAnalisis estado = EstadoAnalisis.PENDIENTE;

    @Column(length = 4000)
    private String descripcion;

    @Column(length = 4000)
    private String comentarios;

    @Column(nullable = false)
    private LocalDateTime fechaSolicitud = LocalDateTime.now();

    private LocalDateTime fechaFinalizacion;
}
