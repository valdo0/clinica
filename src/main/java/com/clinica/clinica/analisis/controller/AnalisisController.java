package com.clinica.clinica.analisis.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.clinica.analisis.model.AnalisisRequestDTO;
import com.clinica.clinica.analisis.model.AnalisisResponseDTO;
import com.clinica.clinica.analisis.model.AnalisisUpdateDTO;
import com.clinica.clinica.analisis.service.AnalisisService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/analisis")
public class AnalisisController {

    private final AnalisisService analisisService;

    public AnalisisController(AnalisisService analisisService) {
        this.analisisService = analisisService;
    }

    @GetMapping
    public ResponseEntity<List<AnalisisResponseDTO>> getAllAnalisis() {
        log.info("[GET] Obtener todos los análisis");
        return ResponseEntity.ok(analisisService.getAllAnalisis());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<AnalisisResponseDTO>> getAnalisisByUsuarioId(@PathVariable Long usuarioId) {
        log.info("[GET] Obtener análisis por ID de usuario: {}", usuarioId);
        return ResponseEntity.ok(analisisService.getAnalisisByUsuarioId(usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnalisisResponseDTO> getAnalisisById(@PathVariable Long id) {
        log.info("[GET] Obtener análisis con ID: {}", id);
        Optional<AnalisisResponseDTO> analisis = analisisService.getAnalisisById(id);
        return analisis.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AnalisisResponseDTO> createAnalisis(@Valid @RequestBody AnalisisRequestDTO dto) {
        log.info("[POST] Crear nuevo análisis");
        AnalisisResponseDTO createdAnalisis = analisisService.createAnalisis(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnalisis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnalisisResponseDTO> updateAnalisis(@PathVariable Long id,
            @Valid @RequestBody AnalisisUpdateDTO dto) {
        log.info("[PUT] Actualizar análisis con ID: {}", id);
        try {
            AnalisisResponseDTO updatedAnalisis = analisisService.updateAnalisis(id, dto);
            return ResponseEntity.ok(updatedAnalisis);
        } catch (RuntimeException e) {
            log.error("Error al actualizar análisis: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnalisis(@PathVariable Long id) {
        log.info("[DELETE] Eliminar análisis con ID: {}", id);
        analisisService.deleteAnalisis(id);
        return ResponseEntity.noContent().build();
    }
}
