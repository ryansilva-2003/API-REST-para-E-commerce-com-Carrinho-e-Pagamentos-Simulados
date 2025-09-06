package com.ryanoliveira.ecommerce_api.dto;

import java.util.UUID;

public record CarrinhoItemResponseDto(
        Long idCarrinhoItem,
        UUID idUsuario,
        Long idProduto,
        Integer quantidade
) {}
