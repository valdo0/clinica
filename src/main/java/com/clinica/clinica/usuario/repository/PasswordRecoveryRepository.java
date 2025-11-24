package com.clinica.clinica.usuario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinica.clinica.usuario.model.PasswordRecovery;
import com.clinica.clinica.usuario.model.Usuario;

@Repository
public interface PasswordRecoveryRepository extends JpaRepository<PasswordRecovery, Long> {

    Optional<PasswordRecovery> findByCodigoAndUsado(String codigo, Boolean usado);

    List<PasswordRecovery> findByUsuarioAndUsadoOrderByFechaCreacionDesc(Usuario usuario, Boolean usado);

}
