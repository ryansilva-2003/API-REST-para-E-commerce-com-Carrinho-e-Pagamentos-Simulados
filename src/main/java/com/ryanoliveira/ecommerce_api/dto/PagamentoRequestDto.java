package com.ryanoliveira.ecommerce_api.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record PagamentoRequestDto(
        @NotNull(message = "Esse campo é obrigatório")
        UUID idPedido,

        @NotNull(message = "Valor é obrigatório")
        BigDecimal valor,

        @NotNull(message = "Status é obrigatório")
        String statusPagamento

) {}
