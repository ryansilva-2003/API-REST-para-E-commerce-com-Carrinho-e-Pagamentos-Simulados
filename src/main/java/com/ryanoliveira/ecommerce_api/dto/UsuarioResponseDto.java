package com.ryanoliveira.ecommerce_api.dto;

import java.time.LocalDate;
import java.util.UUID;

public record UsuarioResponseDto(
        UUID idUsuario,
        String nome,
        String email,
        String celular,
        LocalDate dataNasc
){}
