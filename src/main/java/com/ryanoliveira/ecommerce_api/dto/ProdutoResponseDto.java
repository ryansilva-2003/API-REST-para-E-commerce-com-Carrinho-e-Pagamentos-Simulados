package com.ryanoliveira.ecommerce_api.dto;

import java.math.BigDecimal;

public record ProdutoResponseDto(
        Long idProduto,
        String nomeProduto,
        String descricao,
        BigDecimal preco,
        String imagem,
        Integer estoque,
        String categoria
) {
}
