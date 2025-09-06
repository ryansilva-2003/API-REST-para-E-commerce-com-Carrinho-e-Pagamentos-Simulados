package com.ryanoliveira.ecommerce_api.repository;


import com.ryanoliveira.ecommerce_api.model.CarrinhoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;


public interface CarrinhoItemRepository extends JpaRepository<CarrinhoItem, Long> {
    List<CarrinhoItem> findByUsuarioId(UUID idUsuario);
}
