package com.clinica.clinica.laboratorio.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import com.clinica.clinica.laboratorio.dto.LaboratorioRequestDTO;
import com.clinica.clinica.laboratorio.model.Laboratorio;
import com.clinica.clinica.laboratorio.model.TipoAnalisis;
import com.clinica.clinica.laboratorio.repository.LaboratorioRepository;
import com.clinica.clinica.laboratorio.repository.TipoAnalisisRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class LaboratorioService {

    private final LaboratorioRepository laboratorioRepository;
    private final TipoAnalisisRepository tipoAnalisisRepository;

    public LaboratorioService(LaboratorioRepository laboratorioRepository, TipoAnalisisRepository tipoAnalisisRepository) {
        this.laboratorioRepository = laboratorioRepository;
        this.tipoAnalisisRepository = tipoAnalisisRepository;
    }

    public Laboratorio crearLaboratorio(LaboratorioRequestDTO dto) {
        log.info("Creando nuevo laboratorio con nombre: {}", dto.getNombre());
        Laboratorio laboratorio = new Laboratorio();
        if(laboratorioRepository.existsByNombreIgnoreCase(dto.getNombre())) {
            throw new RuntimeException("Ya existe un laboratorio con el nombre: " + dto.getNombre());
        }
        laboratorio.setNombre(dto.getNombre());
        laboratorio.setDireccion(dto.getDireccion());
        laboratorio.setTelefono(dto.getTelefono());
        laboratorio.setHabilitado(dto.getHabilitado());

        if (dto.getTiposAnalisisIds() != null && !dto.getTiposAnalisisIds().isEmpty()) {
            Set<TipoAnalisis> tipos = new HashSet<>(tipoAnalisisRepository.findAllById(dto.getTiposAnalisisIds()));
            laboratorio.setTiposAnalisis(tipos);
        }
        return laboratorioRepository.save(laboratorio);
    }

    public List<Laboratorio> getAllLaboratorios() {
        log.info("Obteniendo todos los laboratorios");
        return laboratorioRepository.findAll();
    }

    public Laboratorio actualizarLaboratorio(Long id, LaboratorioRequestDTO dto) {
        log.info("Actualizando laboratorio con ID: {}", id);
        Laboratorio laboratorio = laboratorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laboratorio no encontrado con ID: " + id));

        laboratorio.setNombre(dto.getNombre());
        laboratorio.setDireccion(dto.getDireccion());
        laboratorio.setTelefono(dto.getTelefono());
        laboratorio.setHabilitado(dto.getHabilitado());

        if (dto.getTiposAnalisisIds() != null) {
            Set<TipoAnalisis> tipos = new HashSet<>(tipoAnalisisRepository.findAllById(dto.getTiposAnalisisIds()));
            laboratorio.setTiposAnalisis(tipos);
        }
        return  laboratorioRepository.save(laboratorio);
    }

    public void deshabiliarLaboratorio(Long id) {
        log.info("Deshabilitando laboratorio con ID: {}", id);
        Laboratorio laboratorio = laboratorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laboratorio no encontrado con ID: " + id));
        
        laboratorio.setHabilitado(false);
        laboratorioRepository.save(laboratorio);
    }
}
