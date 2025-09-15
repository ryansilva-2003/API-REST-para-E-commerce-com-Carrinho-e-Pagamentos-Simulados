package com.ryanoliveira.ecommerce_api.controller;

import com.ryanoliveira.ecommerce_api.dto.CarrinhoItemRequestDto;
import com.ryanoliveira.ecommerce_api.dto.CarrinhoItemResponseDto;
import com.ryanoliveira.ecommerce_api.model.CarrinhoItem;
import com.ryanoliveira.ecommerce_api.service.CarrinhoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/Carrinho")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService){
        this.carrinhoService = carrinhoService;
    }

    @PostMapping("/{idUsuario}/item")
    public ResponseEntity<CarrinhoItemResponseDto> adicionarItem(@PathVariable UUID idUsuario, @RequestBody @Valid CarrinhoItemRequestDto itemRequestDto){
        CarrinhoItem item = CarrinhoService.adicionarItem(idUsuario, itemRequestDto)
    }
}
