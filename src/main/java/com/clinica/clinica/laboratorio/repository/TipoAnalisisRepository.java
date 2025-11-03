package com.clinica.clinica.laboratorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.clinica.laboratorio.model.TipoAnalisis;

public interface TipoAnalisisRepository extends JpaRepository<TipoAnalisis, Long> {
    boolean existsByNombreIgnoreCase(String nombre);

    boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Long id);
    
}
