package com.ryanoliveira.ecommerce_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record UsuarioRequestDto (
        @NotBlank(message = "Esse campo é obrigatório")
        String nome,

        @Email(message = "Email deve ser válido")
        @NotBlank(message = "Esse campo é obrigatório")
        String email,

        @NotBlank(message = "Senha obrigatória")
        @Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
        String senha,

        LocalDate dataNasc,

        @NotBlank(message = "Número de celular é obrigatório")
        String celular

){}