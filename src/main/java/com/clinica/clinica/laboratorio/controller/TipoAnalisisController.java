package com.clinica.clinica.laboratorio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.clinica.laboratorio.model.TipoAnalisis;
import com.clinica.clinica.laboratorio.service.TipoAnalisisService;

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



@Slf4j
@RestController
@RequestMapping("/api/tipos-analisis")
public class TipoAnalisisController {

    private final TipoAnalisisService tipoAnalisisService;

    public TipoAnalisisController(TipoAnalisisService tipoAnalisisService) {
        this.tipoAnalisisService = tipoAnalisisService;
    }

    @GetMapping
    public ResponseEntity<List<TipoAnalisis>> obtenerTiposAnalisis(){
    
        log.info("[GET] Listar tipos de analisis");
        List<TipoAnalisis> analisis;
        analisis = tipoAnalisisService.getTipoAnalisis();
        return ResponseEntity.ok(analisis);
    }

    @PostMapping
    public ResponseEntity<TipoAnalisis> creatTipoAnalisis(@Valid @RequestBody TipoAnalisis tipoAnalisis) {
        log.info("[POST] Crear tipo de analisis");
        TipoAnalisis entity = tipoAnalisisService.crearTipoAnalisis(tipoAnalisis);
        log.info("Tipo de analisis creado con ID: {}", entity.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TipoAnalisis> getMethodName(@PathVariable Long id) {
        log.info("[GET] Obtener tipo de analisis con ID: {}", id);
        TipoAnalisis entity = tipoAnalisisService.obtenerTipoAnalisisPorId(id);
        if(entity == null) {
            log.warn("Tipo de analisis con ID: {} no encontrado", id);
            return ResponseEntity.notFound().build();
        }
        log.info("Tipo de analisis obtenido con ID: {}", id);
        return ResponseEntity.ok(entity);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TipoAnalisis> modificarTipoAnalisis(@PathVariable Long id,@Valid @RequestBody TipoAnalisis entity) {
        log.info("[PUT] Modificar tipo de analisis con ID: {}", id);
        return ResponseEntity.ok(tipoAnalisisService.actualizarTipoAnalisis(id,entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTipoAnalisis(@PathVariable Long id) {
        log.info("[DELETE] Eliminar tipo de analisis con ID: {}", id);
        tipoAnalisisService.eliminarTipoAnalisis(id);
        log.info("Tipo de analisis eliminado con ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}
