package com.ryanoliveira.ecommerce_api.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CarrinhoItemRequestDto(
        @NotNull(message = "Usuário é obrigatório")
        UUID idUsuario,

        @NotNull(message = "Produto é obrigatório")
        Long idProduto,

        @NotNull(message = "Quantidade é obrigatória")
        Integer quantidade
) {}
