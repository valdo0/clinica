package com.clinica.clinica.laboratorio.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.clinica.laboratorio.model.Laboratorio;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {
    boolean existsByNombreIgnoreCase(String nombre);
}