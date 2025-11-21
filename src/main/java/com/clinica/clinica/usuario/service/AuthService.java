package com.clinica.clinica.usuario.service;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.clinica.clinica.usuario.model.LoginRequestDTO;
import com.clinica.clinica.usuario.model.LoginResponseDTO;
import com.clinica.clinica.usuario.model.Usuario;
import com.clinica.clinica.usuario.repository.UsuarioRepository;

@Slf4j
@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<LoginResponseDTO> login(LoginRequestDTO loguinDTO) {
        log.info("Login request: {}", loguinDTO);
        Optional<Usuario> usuario = usuarioRepository.findByEmailAndPassword(loguinDTO.getEmail(),
                loguinDTO.getPassword());
        if (usuario.isEmpty()) {
            log.warn("Login failed for email: {}", loguinDTO.getEmail());
            throw new IllegalArgumentException("Invalid email or password");
        }
        return Optional.of(new LoginResponseDTO(
                usuario.get().getId(),
                usuario.get().getNombre(),
                usuario.get().getEmail(),
                usuario.get().getTelefono(),
                usuario.get().getFechaRegistro(),
                usuario.get().getRol()));
    }
}
