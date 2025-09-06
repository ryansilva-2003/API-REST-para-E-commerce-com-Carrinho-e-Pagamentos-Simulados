package com.ryanoliveira.ecommerce_api.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record PedidoItemRequestDto(
        @NotNull(message = "Produto é obrigatório")
        Long idProduto,

        @NotNull(message = "Quantidade é obrigatória")
        Integer quantidade,

        @NotNull(message = "Preço é obrigatório")
        BigDecimal precoUnitario
) {}
