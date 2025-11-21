package com.clinica.clinica.usuario.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.clinica.usuario.model.LoginRequestDTO;
import com.clinica.clinica.usuario.model.LoginResponseDTO;
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
}
