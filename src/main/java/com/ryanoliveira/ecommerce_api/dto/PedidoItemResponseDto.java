package com.ryanoliveira.ecommerce_api.dto;

import java.math.BigDecimal;

public record PedidoItemResponseDto(
        Long idPedidoItem,
        Long idProduto,
        Integer quantidade,
        BigDecimal precoUnitario
) {}
