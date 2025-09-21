package com.ryanoliveira.ecommerce_api.controller;

import com.ryanoliveira.ecommerce_api.dto.PedidoFinalRequestDto;
import com.ryanoliveira.ecommerce_api.dto.PedidoFinalResponseDto;
import com.ryanoliveira.ecommerce_api.dto.PedidoItemResponseDto;
import com.ryanoliveira.ecommerce_api.model.PedidoFinal;
import com.ryanoliveira.ecommerce_api.model.PedidoItem;
import com.ryanoliveira.ecommerce_api.model.StatusPedido;
import com.ryanoliveira.ecommerce_api.service.PedidoFinalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("/pedidos")
public class PedidoFinalController {

    private final PedidoFinalService pedidoFinalService;

    public PedidoFinalController(PedidoFinalService pedidoFinalService) {
        this.pedidoFinalService = pedidoFinalService;
    }

    @PostMapping
    public ResponseEntity<PedidoFinalResponseDto> criarPedido(@RequestBody @Valid PedidoFinalRequestDto pedidoFinalRequestDto) {
        PedidoFinal pedido = new PedidoFinal();
        pedido.setUsuario(pedidoFinalRequestDto.usuario());
        List<PedidoItem> itens = (List<PedidoItem>) pedidoFinalRequestDto.itens().stream();

        PedidoFinal salvo = pedidoFinalService.save(pedido);

        PedidoFinalResponseDto dto = new PedidoFinalResponseDto(
                salvo.getIdPedidoFinal(),
                salvo.getUsuario().getIdUsuario(),
                salvo.getItens().stream()
                        .map(item -> new PedidoItemResponseDto(
                                item.getIdPedidoItem(),
                                item.getProduto().getIdProduto(),
                                item.getQuantidade(),
                                item.getPrecoUnitario(),
                                item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()))
                        ))
                        .toList(),
                salvo.getTotal(),
                salvo.getStatusPedido(),
                salvo.getDataCriacao()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<PedidoFinalResponseDto>> listarPedidos() {
        List<PedidoFinal> pedidos = pedidoFinalService.listarTodos();
        List<PedidoFinalResponseDto> dtos = pedidos.stream().map(this::mapToDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{idPedidoFinal}")
    public ResponseEntity<PedidoFinalResponseDto> findById(@PathVariable Long idPedidoFinal) {
        return pedidoFinalService.findById(idPedidoFinal)
                .map(this::mapToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{idPedidoFinal}/status")
    public ResponseEntity<PedidoFinalResponseDto> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusPedido novoStatus
    ) {
        PedidoFinal atualizado = pedidoFinalService.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(mapToDto(atualizado));
    }

    @DeleteMapping("/{idPedidoFinal}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pedidoFinalService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private PedidoFinalResponseDto mapToDto(PedidoFinal pedido) {
        List<PedidoItemResponseDto> itensDto = pedido.getItens().stream()
                .map(item -> new PedidoItemResponseDto(
                        item.getIdPedidoItem(),
                        item.getProduto().getIdProduto(),
                        item.getQuantidade(),
                        item.getPrecoUnitario(),
                        item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()))
                ))
                .collect(Collectors.toList());

        return new PedidoFinalResponseDto(
                pedido.getIdPedidoFinal(),
                pedido.getUsuario().getIdUsuario(),
                itensDto,
                pedido.getTotal(),
                pedido.getStatusPedido(),
                pedido.getDataCriacao()
        );

    }
}
