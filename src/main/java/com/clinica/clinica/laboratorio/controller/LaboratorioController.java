package com.clinica.clinica.laboratorio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.clinica.laboratorio.dto.LaboratorioRequestDTO;
import com.clinica.clinica.laboratorio.model.Laboratorio;
import com.clinica.clinica.laboratorio.service.LaboratorioService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@Slf4j
@RestController
@RequestMapping("/api/laboratorios")
public class LaboratorioController {

    private final LaboratorioService laboratorioService;
    public LaboratorioController(LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }

    @GetMapping
    public ResponseEntity<List<Laboratorio>> getAllLaboratorios() {
        log.info("[GET] Listar laboratorios");
        List<Laboratorio> laboratorios = laboratorioService.getAllLaboratorios();
        return ResponseEntity.ok(laboratorios);
    }
    
    @PostMapping
    public ResponseEntity<Laboratorio> postMethodName(@Valid @RequestBody LaboratorioRequestDTO  entity) {
        log.info("[POST] Crear laboratorio");
        Laboratorio laboratorio=laboratorioService.crearLaboratorio(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(laboratorio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Laboratorio> modificarLaboratorio(@PathVariable Long id, @Valid @RequestBody LaboratorioRequestDTO entity) {
        
        log.info("[PUT] Modificar laboratorio con ID: {}", id);
        Laboratorio laboratorio=laboratorioService.actualizarLaboratorio(id,entity);
        log.info("Laboratorio modificado con ID: {}", id);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(laboratorio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLaboratorio(@PathVariable Long id) {
        log.info("[DELETE] Eliminar laboratorio con ID: {}", id);
        laboratorioService.deshabiliarLaboratorio(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laboratorio> getById(@PathVariable Long id) {
        
        log.info("[GET] Obtener laboratorio con ID: {}", id);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(laboratorioService.getById(id));
    }
    
}
