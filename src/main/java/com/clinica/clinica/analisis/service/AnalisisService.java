package com.clinica.clinica.analisis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.clinica.clinica.analisis.model.Analisis;
import com.clinica.clinica.analisis.model.AnalisisRequestDTO;
import com.clinica.clinica.analisis.model.AnalisisResponseDTO;
import com.clinica.clinica.analisis.repository.AnalisisRepository;
import com.clinica.clinica.laboratorio.model.Laboratorio;
import com.clinica.clinica.laboratorio.model.TipoAnalisis;
import com.clinica.clinica.laboratorio.repository.LaboratorioRepository;
import com.clinica.clinica.laboratorio.repository.TipoAnalisisRepository;
import com.clinica.clinica.usuario.model.Usuario;
import com.clinica.clinica.usuario.repository.UsuarioRepository;

@Service
public class AnalisisService {

    private final AnalisisRepository analisisRepository;
    private final LaboratorioRepository laboratorioRepository;
    private final TipoAnalisisRepository tipoAnalisisRepository;
    private final UsuarioRepository usuarioRepository;

    public AnalisisService(AnalisisRepository analisisRepository,
            LaboratorioRepository laboratorioRepository,
            TipoAnalisisRepository tipoAnalisisRepository,
            UsuarioRepository usuarioRepository) {
        this.analisisRepository = analisisRepository;
        this.laboratorioRepository = laboratorioRepository;
        this.tipoAnalisisRepository = tipoAnalisisRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<AnalisisResponseDTO> getAllAnalisis() {
        return analisisRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public Optional<AnalisisResponseDTO> getAnalisisById(Long id) {
        return analisisRepository.findById(id).map(this::mapToResponseDTO);
    }

    public AnalisisResponseDTO createAnalisis(AnalisisRequestDTO dto) {
        Laboratorio laboratorio = laboratorioRepository.findById(dto.getLaboratorioId())
                .orElseThrow(() -> new RuntimeException("Laboratorio no encontrado"));
        TipoAnalisis tipoAnalisis = tipoAnalisisRepository.findById(dto.getTipoAnalisisId())
                .orElseThrow(() -> new RuntimeException("Tipo de análisis no encontrado"));
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Analisis analisis = new Analisis();
        analisis.setLaboratorio(laboratorio);
        analisis.setTipoAnalisis(tipoAnalisis);
        analisis.setUsuario(usuario);
        analisis.setDescripcion(dto.getDescripcion());
        analisis.setComentarios(dto.getComentarios());

        Analisis savedAnalisis = analisisRepository.save(analisis);
        return mapToResponseDTO(savedAnalisis);
    }

    public AnalisisResponseDTO updateAnalisis(Long id, AnalisisRequestDTO dto) {
        Analisis updatedAnalisis = analisisRepository.findById(id).map(analisis -> {
            if (dto.getLaboratorioId() != null) {
                Laboratorio laboratorio = laboratorioRepository.findById(dto.getLaboratorioId())
                        .orElseThrow(() -> new RuntimeException("Laboratorio no encontrado"));
                analisis.setLaboratorio(laboratorio);
            }
            if (dto.getTipoAnalisisId() != null) {
                TipoAnalisis tipoAnalisis = tipoAnalisisRepository.findById(dto.getTipoAnalisisId())
                        .orElseThrow(() -> new RuntimeException("Tipo de análisis no encontrado"));
                analisis.setTipoAnalisis(tipoAnalisis);
            }
            if (dto.getUsuarioId() != null) {
                Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
                analisis.setUsuario(usuario);
            }

            if (dto.getDescripcion() != null)
                analisis.setDescripcion(dto.getDescripcion());
            if (dto.getComentarios() != null)
                analisis.setComentarios(dto.getComentarios());

            return analisisRepository.save(analisis);
        }).orElseThrow(() -> new RuntimeException("Analisis not found with id " + id));

        return mapToResponseDTO(updatedAnalisis);
    }

    public void deleteAnalisis(Long id) {
        analisisRepository.deleteById(id);
    }

    private AnalisisResponseDTO mapToResponseDTO(Analisis analisis) {
        AnalisisResponseDTO dto = new AnalisisResponseDTO();
        dto.setId(analisis.getId());
        dto.setLaboratorioNombre(analisis.getLaboratorio().getNombre());
        dto.setTipoAnalisisNombre(analisis.getTipoAnalisis().getNombre());
        dto.setUsuarioNombre(analisis.getUsuario().getNombre());
        dto.setUsuarioEmail(analisis.getUsuario().getEmail());
        dto.setEstado(analisis.getEstado());
        dto.setDescripcion(analisis.getDescripcion());
        dto.setComentarios(analisis.getComentarios());
        dto.setFechaSolicitud(analisis.getFechaSolicitud());
        dto.setFechaFinalizacion(analisis.getFechaFinalizacion());
        return dto;
    }
}
