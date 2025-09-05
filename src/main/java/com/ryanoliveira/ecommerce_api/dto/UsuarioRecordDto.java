package com.ryanoliveira.ecommerce_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRecordDto(@NotBlank String name,
                               @NotBlank @Email String email,
                               @NotBlank String celular) {
}
