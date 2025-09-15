package com.ryanoliveira.ecommerce_api.service;

import com.ryanoliveira.ecommerce_api.model.PedidoFinal;
import com.ryanoliveira.ecommerce_api.model.StatusPedido;
import com.ryanoliveira.ecommerce_api.repository.PedidoFinalRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoFinalService {

    private final PedidoFinalRepository pedidoFinalRepository;

    public PedidoFinalService(PedidoFinalRepository pedidoFInalRepository){
        this.pedidoFinalRepository = pedidoFInalRepository;
    }

    @Transactional
    public PedidoFinal save(PedidoFinal pedidoFinal){
        return pedidoFinalRepository.save(pedidoFinal);
    }

    public List<PedidoFinal> listarTodos(){
        return pedidoFinalRepository.findAll();
    }

    public Optional<PedidoFinal> buscarPorId(Long id){
        return pedidoFinalRepository.findById(id);
    }

    @Transactional
    public PedidoFinal atualizarStatus (Long idPedidoFinal, StatusPedido novoStatus){
        PedidoFinal pedidoFinal = pedidoFinalRepository.findById(idPedidoFinal)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com id: " + idPedidoFinal));
        pedidoFinal.setStatusPedido(novoStatus);
        return pedidoFinalRepository.save(pedidoFinal);
    }

    @Transactional
    public void deletar(Long id){
        if(!pedidoFinalRepository.existsById(id)){
            throw new RuntimeException("Pedido não encontrado com esse ID: " + id);
        }
        pedidoFinalRepository.deleteById(id);
    }
    
}
