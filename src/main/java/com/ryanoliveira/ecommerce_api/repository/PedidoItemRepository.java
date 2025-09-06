package com.ryanoliveira.ecommerce_api.repository;

import com.ryanoliveira.ecommerce_api.model.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long> {
    List<PedidoItem> findByPedidoId(Long pedidoId);
}
