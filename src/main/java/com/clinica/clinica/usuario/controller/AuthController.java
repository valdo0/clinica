package com.clinica.clinica.usuario.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.clinica.usuario.model.DTOS.LoginRequestDTO;
import com.clinica.clinica.usuario.model.DTOS.LoginResponseDTO;
import com.clinica.clinica.usuario.model.DTOS.MessageResponseDTO;
import com.clinica.clinica.usuario.model.DTOS.PasswordRecoveryResponseDTO;
import com.clinica.clinica.usuario.model.DTOS.RegisterRequestDTO;
import com.clinica.clinica.usuario.model.DTOS.RequestPasswordRecoveryDTO;
import com.clinica.clinica.usuario.model.DTOS.ResetPasswordDTO;
import com.clinica.clinica.usuario.service.AuthService;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequest) {
        log.info("Login request: {}", loginRequest);
        Optional<LoginResponseDTO> responseOpt = authService.login(loginRequest);

        return ResponseEntity.ok(responseOpt);
    }

    @PostMapping("/register")
    public ResponseEntity<Optional<LoginResponseDTO>> register(@RequestBody RegisterRequestDTO registerRequest) {
        log.info("Register request: {}", registerRequest);
        Optional<LoginResponseDTO> responseOpt = authService.register(registerRequest);

        return ResponseEntity.ok(responseOpt);
    }

    @PostMapping("/request-password-recovery")
    public ResponseEntity<PasswordRecoveryResponseDTO> requestPasswordRecovery(
            @RequestBody RequestPasswordRecoveryDTO request) {
        log.info("Request password recovery: {}", request.getEmail());
        PasswordRecoveryResponseDTO response = authService.requestPasswordRecovery(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<MessageResponseDTO> resetPassword(@RequestBody ResetPasswordDTO request) {
        log.info("Reset password: {}", request.getEmail());
        MessageResponseDTO response = authService.resetPassword(request);
        return ResponseEntity.ok(response);
    }
}
