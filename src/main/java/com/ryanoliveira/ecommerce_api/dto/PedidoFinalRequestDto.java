package com.ryanoliveira.ecommerce_api.dto;

import com.ryanoliveira.ecommerce_api.model.Usuario;
import jakarta.validation.constraints.NotNull;
import java.util.List;


public record PedidoFinalRequestDto(
        @NotNull
        Usuario usuario,
        @NotNull
        List<PedidoItemRequestDto> itens
) {}
