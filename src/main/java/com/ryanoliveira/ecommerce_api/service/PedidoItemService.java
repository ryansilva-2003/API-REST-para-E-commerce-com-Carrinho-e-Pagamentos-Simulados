package com.ryanoliveira.ecommerce_api.service;

import com.ryanoliveira.ecommerce_api.model.PedidoItem;
import com.ryanoliveira.ecommerce_api.repository.PedidoItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoItemService {

    private final PedidoItemRepository pedidoItemRepository;

    public PedidoItemService (PedidoItemRepository pedidoItemRepository){
        this.pedidoItemRepository = pedidoItemRepository;
    }

    @Transactional
    public PedidoItem save(PedidoItem pedidoItem){
        return pedidoItemRepository.save(pedidoItem);
    }

    public List<PedidoItem> listarTodos(){
        return pedidoItemRepository.findAll();
    }

    public Optional<PedidoItem> buscarPorId(Long id){
        return pedidoItemRepository.findById(id);
    }

    @Transactional
    public PedidoItem atualizarQuantidade(Long id, int novaQuantidade) {
        PedidoItem pedidoItem = pedidoItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de pedido não encontrado com ID: " + id));
        pedidoItem.setQuantidade(novaQuantidade);
        return pedidoItemRepository.save(pedidoItem);
    }

    @Transactional
    public void deletar(Long id) {
        if (!pedidoItemRepository.existsById(id)) {
            throw new RuntimeException("Item de pedido não encontrado com ID: " + id);
        }
        pedidoItemRepository.deleteById(id);
    }

}
