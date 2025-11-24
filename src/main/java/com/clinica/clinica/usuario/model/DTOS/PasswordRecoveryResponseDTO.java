package com.clinica.clinica.usuario.model.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordRecoveryResponseDTO {

    private String mensaje;
    private String codigo;

}
