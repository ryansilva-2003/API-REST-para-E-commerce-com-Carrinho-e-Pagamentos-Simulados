package com.ryanoliveira.ecommerce_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProdutoRequestDto(
        @NotBlank(message = "Nome é obrigatório")
        String nomeProduto,

        @NotBlank(message = "Descrição é obrigatória")
        String descricao,

        @NotNull(message = "Preço é obrigatório")
        BigDecimal preco,

        String imagem,

        @NotNull(message = "Estoque é obrigatório")
        Integer estoque,

        @NotBlank(message = "Esse campo é obrigatório")
        String categoria

) {}