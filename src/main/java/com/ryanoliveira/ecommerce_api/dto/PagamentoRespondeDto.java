package com.ryanoliveira.ecommerce_api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PagamentoRespondeDto(
        UUID idPagamento,
        Long idPedidoFinal,
        BigDecimal valor,
        String statusPagamento,
        LocalDateTime dataPagamento
) {}
