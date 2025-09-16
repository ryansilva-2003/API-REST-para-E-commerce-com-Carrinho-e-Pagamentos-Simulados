package com.ryanoliveira.ecommerce_api.controller;

import com.ryanoliveira.ecommerce_api.dto.CarrinhoItemRequestDto;
import com.ryanoliveira.ecommerce_api.dto.CarrinhoItemResponseDto;
import com.ryanoliveira.ecommerce_api.model.CarrinhoItem;
import com.ryanoliveira.ecommerce_api.service.CarrinhoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Carrinho")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService){
        this.carrinhoService = carrinhoService;
    }

    @PostMapping("/{idUsuario}/item")
    public ResponseEntity<CarrinhoItemResponseDto> adicionarItem(@PathVariable UUID idUsuario, @RequestBody @Valid CarrinhoItemRequestDto carrinhoItemRequestDto){
        CarrinhoItem item = carrinhoService.adicionarItem(
                idUsuario,
                carrinhoItemRequestDto.idProduto(),
                carrinhoItemRequestDto.quantidade()
        );
        return ResponseEntity.ok(mapToDto(item));
    }

    private CarrinhoItemResponseDto mapToDto(CarrinhoItem item) {
        return new CarrinhoItemResponseDto(
                item.getIdCarrinhoItem(),
                item.getCarrinho().getUsuario().getIdUsuario(),
                item.getProduto().getIdProduto(),
                item.getQuantidade(),
                item.getProduto().getPreco()
        );
    }

    @DeleteMapping("/{idUsuario}/item/{idProduto}")
    public ResponseEntity<Void> removerItem(@PathVariable UUID idUsuario, @PathVariable Long idProduto){
        carrinhoService.removerItem(idUsuario, idProduto);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{idUsuario}/itens")
    public ResponseEntity<List<CarrinhoItemResponseDto>> listarItens(
            @PathVariable UUID idUsuario) {

        List<CarrinhoItem> itens = carrinhoService.listarItens(idUsuario);
        List<CarrinhoItemResponseDto> itensDto = itens.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(itensDto);
    }

    @PutMapping("/{idUsuario}/item/{idProduto}")
    public ResponseEntity<CarrinhoItemResponseDto> atualizarItem(@PathVariable UUID idUsuario, @PathVariable Long idProduto, @RequestBody @Valid CarrinhoItemRequestDto carrinhoItemRequestDto){
        CarrinhoItem itemAtualizado = carrinhoService.atualizarItem(
                idUsuario,
                idProduto,
                carrinhoItemRequestDto.quantidade()
        );
        return ResponseEntity.ok(mapToDto(itemAtualizado));
    }

    @GetMapping("/{idUsuario}/total")
    public ResponseEntity<String> calcularTotal(@PathVariable UUID idUsuario){
        BigDecimal total = carrinhoService.calcularTotal(idUsuario);
        return ResponseEntity.ok(total.toString());
    }

    @DeleteMapping("/{idUsuario}/itens")
    public ResponseEntity<Void> limparCarrinho(@PathVariable UUID idUsuario){
        carrinhoService.limparCarrinho(idUsuario);
        return ResponseEntity.noContent().build();
    }
}
