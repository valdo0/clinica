package com.clinica.clinica.analisis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinica.clinica.analisis.model.Analisis;

@Repository
public interface AnalisisRepository extends JpaRepository<Analisis, Long> {
    List<Analisis> findByUsuarioId(Long usuarioId);
}
