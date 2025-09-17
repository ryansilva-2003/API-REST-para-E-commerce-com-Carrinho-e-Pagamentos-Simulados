package com.ryanoliveira.ecommerce_api.controller;

import com.ryanoliveira.ecommerce_api.service.PedidoFinalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/pedidos")
public class PedidoFinalController {

    private final PedidoFinalService pedidoFinalService;

    public PedidoFinalController(PedidoFinalService pedidoFinalService){
        this.pedidoFinalService = pedidoFinalService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDto>
}
