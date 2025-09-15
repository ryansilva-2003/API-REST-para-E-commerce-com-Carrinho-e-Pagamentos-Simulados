package com.ryanoliveira.ecommerce_api.controller;

import com.ryanoliveira.ecommerce_api.dto.ProdutoRequestDto;
import com.ryanoliveira.ecommerce_api.dto.ProdutoResponseDto;
import com.ryanoliveira.ecommerce_api.model.Produto;
import com.ryanoliveira.ecommerce_api.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDto> salvar(@RequestBody @Valid ProdutoRequestDto produtoRequestDto) {
        Produto produto = new Produto();
        produto.setNomeProduto(produtoRequestDto.nomeProduto());
        produto.setDescricao(produtoRequestDto.descricao());
        produto.setPreco(produtoRequestDto.preco());
        produto.setImagem(produtoRequestDto.imagem());
        produto.setEstoque(produtoRequestDto.estoque());
        produto.setCategoria(produtoRequestDto.categoria());

        Produto novoProduto = produtoService.save(produto);

        ProdutoResponseDto dto = new ProdutoResponseDto(
                novoProduto.getIdProduto(),
                novoProduto.getNomeProduto(),
                novoProduto.getDescricao(),
                novoProduto.getPreco(),
                novoProduto.getImagem(),
                novoProduto.getEstoque(),
                novoProduto.getCategoria()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoResponseDto> findById(@PathVariable Long idProduto) {
        Produto produto = produtoService.findById(idProduto);

        ProdutoResponseDto dto = new ProdutoResponseDto(
             produto.getIdProduto(),
             produto.getNomeProduto(),
             produto.getDescricao(),
             produto.getPreco(),
             produto.getImagem(),
             produto.getEstoque(),
             produto.getCategoria()
        );
        return ResponseEntity.ok(dto);
    }
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> listarTodos() {
        List<Produto> produtos = produtoService.listarTodos();
        List<ProdutoResponseDto> produtosDto = produtos.stream()
                .map(produto -> new ProdutoResponseDto(
                        produto.getIdProduto(),
                        produto.getNomeProduto(),
                        produto.getDescricao(),
                        produto.getPreco(),
                        produto.getImagem(),
                        produto.getEstoque(),
                        produto.getCategoria()
                ))
                .toList();
        return ResponseEntity.ok(produtosDto);
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Void> deletar (@PathVariable Long idProduto){
        produtoService.deletar(idProduto);
        return ResponseEntity.noContent().build();
    }
}