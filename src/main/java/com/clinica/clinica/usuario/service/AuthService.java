package com.clinica.clinica.usuario.service;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.clinica.clinica.usuario.model.PasswordRecovery;
import com.clinica.clinica.usuario.model.Usuario;
import com.clinica.clinica.usuario.model.DTOS.LoginRequestDTO;
import com.clinica.clinica.usuario.model.DTOS.LoginResponseDTO;
import com.clinica.clinica.usuario.model.DTOS.MessageResponseDTO;
import com.clinica.clinica.usuario.model.DTOS.PasswordRecoveryResponseDTO;
import com.clinica.clinica.usuario.model.DTOS.RegisterRequestDTO;
import com.clinica.clinica.usuario.model.DTOS.RequestPasswordRecoveryDTO;
import com.clinica.clinica.usuario.model.DTOS.ResetPasswordDTO;
import com.clinica.clinica.usuario.repository.PasswordRecoveryRepository;
import com.clinica.clinica.usuario.repository.UsuarioRepository;

@Slf4j
@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordRecoveryRepository passwordRecoveryRepository;

    public AuthService(UsuarioRepository usuarioRepository, PasswordRecoveryRepository passwordRecoveryRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordRecoveryRepository = passwordRecoveryRepository;
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

    public Optional<LoginResponseDTO> register(RegisterRequestDTO registerRequest) {
        log.info("Register request: {}", registerRequest);
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(registerRequest.getEmail());
        if (usuarioOpt.isPresent()) {
            log.warn("Usuario ya existe: {}", registerRequest.getEmail());
            throw new IllegalArgumentException("User already exists");
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(registerRequest.getNombre());
        usuario.setEmail(registerRequest.getEmail());
        usuario.setPassword(registerRequest.getPassword());
        usuario.setTelefono(registerRequest.getTelefono());
        usuario.setRol("PACIENTE");
        Usuario user = usuarioRepository.save(usuario);
        return Optional.of(new LoginResponseDTO(
                user.getId(),
                user.getNombre(),
                user.getEmail(),
                user.getTelefono(),
                user.getFechaRegistro(),
                user.getRol()));
    }

    public PasswordRecoveryResponseDTO requestPasswordRecovery(RequestPasswordRecoveryDTO request) {
        log.info("Password recovery request for email: {}", request.getEmail());

        // Buscar usuario por email
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());
        if (usuarioOpt.isEmpty()) {
            log.warn("Password recovery failed: email not found: {}", request.getEmail());
            throw new IllegalArgumentException("Email no encontrado");
        }

        Usuario usuario = usuarioOpt.get();

        // Generar código aleatorio de 6 dígitos
        String codigo = String.format("%06d", new Random().nextInt(1000000));

        // Crear registro de recuperación
        PasswordRecovery recovery = new PasswordRecovery();
        recovery.setUsuario(usuario);
        recovery.setCodigo(codigo);
        recovery.setFechaCreacion(LocalDateTime.now());
        recovery.setUsado(false);

        passwordRecoveryRepository.save(recovery);
        log.info("Password recovery code generated for user: {}", usuario.getEmail());

        // Retornar código (solo para propósitos académicos)
        return new PasswordRecoveryResponseDTO("Código de recuperación generado", codigo);
    }

    public MessageResponseDTO resetPassword(ResetPasswordDTO request) {
        log.info("Reset password request for email: {}", request.getEmail());

        // Buscar código de recuperación
        Optional<PasswordRecovery> recoveryOpt = passwordRecoveryRepository.findByCodigoAndUsado(
                request.getCodigo(), false);

        if (recoveryOpt.isEmpty()) {
            log.warn("Reset password failed: invalid or used code");
            throw new IllegalArgumentException("Código inválido o ya usado");
        }

        PasswordRecovery recovery = recoveryOpt.get();

        // Verificar que el código pertenece al usuario del email
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());
        if (usuarioOpt.isEmpty() || !recovery.getUsuario().getId().equals(usuarioOpt.get().getId())) {
            log.warn("Reset password failed: email doesn't match code");
            throw new IllegalArgumentException("El email no corresponde al código");
        }

        // Verificar que el código no tenga más de 15 minutos
        LocalDateTime expiracion = recovery.getFechaCreacion().plusMinutes(15);
        if (LocalDateTime.now().isAfter(expiracion)) {
            log.warn("Reset password failed: code expired");
            throw new IllegalArgumentException("El código ha expirado (15 minutos)");
        }

        // Actualizar contraseña
        Usuario usuario = usuarioOpt.get();
        usuario.setPassword(request.getNuevaPassword());
        usuarioRepository.save(usuario);

        // Marcar código como usado
        recovery.setUsado(true);
        passwordRecoveryRepository.save(recovery);

        log.info("Password reset successful for user: {}", usuario.getEmail());
        return new MessageResponseDTO("Contraseña actualizada correctamente");
    }
}
