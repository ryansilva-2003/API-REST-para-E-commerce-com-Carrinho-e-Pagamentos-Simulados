package com.ryanoliveira.ecommerce_api.dto;

import com.ryanoliveira.ecommerce_api.model.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PedidoFinalResponseDto(
        UUID idUsuario,
        Long idProduto,
        List<PedidoItemResponseDto> itens,
        BigDecimal total,
        StatusPedido statusPedido,
        LocalDateTime dataCriacao
) {}
