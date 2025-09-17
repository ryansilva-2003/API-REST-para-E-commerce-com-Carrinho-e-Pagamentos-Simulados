package com.ryanoliveira.ecommerce_api.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public record PedidoFinalRequestDto(
        @NotNull
        UUID idUsuario,
        @NotNull
        List<PedidoItemRequestDto> itens
) {}
