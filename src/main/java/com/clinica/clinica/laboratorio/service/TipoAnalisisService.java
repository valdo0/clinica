package com.clinica.clinica.laboratorio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinica.clinica.exception.ResourceNotFoundException;
import com.clinica.clinica.laboratorio.model.TipoAnalisis;
import com.clinica.clinica.laboratorio.repository.TipoAnalisisRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TipoAnalisisService {
    
    private final TipoAnalisisRepository tipoAnalisisRepository;

    public TipoAnalisisService(TipoAnalisisRepository tipoAnalisisRepository) {
        this.tipoAnalisisRepository = tipoAnalisisRepository;
    }

    public List<TipoAnalisis> getTipoAnalisis() {
        log.info("Obteniendo todos los tipos de análisis");
        return tipoAnalisisRepository.findAll();
    }
    
    public TipoAnalisis crearTipoAnalisis(TipoAnalisis tipoAnalisis) {
        log.info("Creando tipo de análisis: {}", tipoAnalisis.getNombre());
        if(tipoAnalisisRepository.existsByNombreIgnoreCase(tipoAnalisis.getNombre())) {
            log.warn("El tipo de análisis con nombre '{}' ya existe", tipoAnalisis.getNombre());
            throw new IllegalArgumentException("El tipo de análisis con este nombre ya existe");
        }
        return tipoAnalisisRepository.save(tipoAnalisis);
    }

    public TipoAnalisis obtenerTipoAnalisisPorId(Long id) {
        log.info("Obteniendo tipo de análisis con ID: {}", id);
        return tipoAnalisisRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Tipo de análisis con ID {} no encontrado", id);
                    return new ResourceNotFoundException("Tipo de análisis no encontrado");
                });
    }

    public TipoAnalisis actualizarTipoAnalisis(Long id, TipoAnalisis tipoAnalisisDetalles) {
        log.info("Actualizando tipo de análisis con ID: {}", id);
        TipoAnalisis tipoAnalisisExistente = obtenerTipoAnalisisPorId(id);

        if(tipoAnalisisRepository.existsByNombreIgnoreCaseAndIdNot(tipoAnalisisDetalles.getNombre(), id)) {
            log.warn("El tipo de análisis con nombre '{}' ya existe", tipoAnalisisDetalles.getNombre());
            throw new IllegalArgumentException("El tipo de análisis con este nombre ya existe");
        }
        
        tipoAnalisisExistente.setNombre(tipoAnalisisDetalles.getNombre());
        tipoAnalisisExistente.setDescripcion(tipoAnalisisDetalles.getDescripcion());

        TipoAnalisis actualizado = tipoAnalisisRepository.save(tipoAnalisisExistente);
        log.info("Tipo de análisis con ID: {} actualizado exitosamente", id);
        return actualizado;
    }

    public void eliminarTipoAnalisis(Long id) {
        log.info("Eliminando tipo de análisis con ID: {}", id);
        TipoAnalisis tipoAnalisisExistente = obtenerTipoAnalisisPorId(id);
        tipoAnalisisRepository.delete(tipoAnalisisExistente);
        log.info("Tipo de análisis con ID: {} eliminado exitosamente", id);
    }

}
