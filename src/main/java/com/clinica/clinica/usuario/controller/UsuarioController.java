package com.clinica.clinica.usuario.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.clinica.usuario.model.PutUsuarioDTO;
import com.clinica.clinica.usuario.model.Usuario;
import com.clinica.clinica.usuario.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {

        log.info("[GET] Listar usuarios");
        List<Usuario> usuarios;
        usuarios = usuarioService.obtenerUsuarios();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        log.info("[GET] Obtener usuario con ID: {}", id);
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> postMethodName(@Valid @RequestBody Usuario entity) {
        log.info("[POST] Crear nuevo usuario");
        return ResponseEntity.ok(usuarioService.crearUsuario(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> putMethodName(@PathVariable Long id,
            @Valid @RequestBody PutUsuarioDTO entity) {
        log.info("[PUT] Actualizar usuario con ID: {}", id);
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMethodName(@PathVariable Long id) {
        log.info("[DELETE] Eliminar usuario con ID: {}", id);
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
