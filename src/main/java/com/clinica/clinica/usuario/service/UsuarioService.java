package com.clinica.clinica.usuario.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.clinica.clinica.exception.ResourceNotFoundException;
import com.clinica.clinica.usuario.model.Usuario;
import com.clinica.clinica.usuario.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(Usuario usuario) {
        log.info("Creando usuario con email: {}", usuario.getEmail());
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            log.warn("El email {} ya está registrado", usuario.getEmail());
            throw new IllegalArgumentException("El email ya está registrado");
        }
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        log.info("Usuario creado con ID: {}", nuevoUsuario.getId());
        return nuevoUsuario;
    }

    public List<Usuario> obtenerUsuarios() {
        log.info("Obteniendo todos los usuarios");
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        log.info("Obteniendo usuario con ID: {}", id);
        return usuarioRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Usuario con ID {} no encontrado", id);
                    return new ResourceNotFoundException("Usuario no encontrado");
                });
    }

    public Usuario actualizarUsuario(Long id, Usuario usuarioDetalles) {
        log.info("Actualizando usuario con ID: {}", id);
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Usuario con ID {} no encontrado", id);
                    return new ResourceNotFoundException("Usuario no encontrado");
                });
        ;
        log.info("Verificando si el email {} ya está registrado comparando con {}", usuarioDetalles.getEmail(),
                usuarioExistente.getEmail());
        if (!usuarioExistente.getEmail().equals(usuarioDetalles.getEmail()) &&
                usuarioRepository.existsByEmail(usuarioDetalles.getEmail())) {
            log.warn("El email {} ya está registrado", usuarioDetalles.getEmail());
            throw new IllegalArgumentException("El email ya está registrado");
        }

        usuarioExistente.setNombre(usuarioDetalles.getNombre());
        usuarioExistente.setEmail(usuarioDetalles.getEmail());
        usuarioExistente.setTelefono(usuarioDetalles.getTelefono());
        usuarioExistente.setRol(usuarioDetalles.getRol());
        Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);
        log.info("Usuario con ID: {} actualizado", id);
        return usuarioActualizado;
    }

    public void eliminarUsuario(Long id) {
        log.info("Eliminando usuario con ID: {}", id);
        if (!usuarioRepository.existsById(id)) {
            log.warn("Usuario con ID {} no encontrado para eliminar", id);
            throw new ResourceNotFoundException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
        log.info("Usuario con ID: {} eliminado", id);
    }

    public Usuario obtenerUsuarioPorEmail(String email) {
        log.info("Obteniendo usuario con email: {}", email);
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("Usuario con email {} no encontrado", email);
                    return new ResourceNotFoundException("Usuario no encontrado");
                });
    }

}
