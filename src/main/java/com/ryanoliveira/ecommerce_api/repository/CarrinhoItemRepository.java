package com.ryanoliveira.ecommerce_api.repository;


import com.ryanoliveira.ecommerce_api.model.Carrinho;
import com.ryanoliveira.ecommerce_api.model.CarrinhoItem;
import com.ryanoliveira.ecommerce_api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface CarrinhoItemRepository extends JpaRepository<CarrinhoItem, Long> {
    List<CarrinhoItem> findByUsuarioId(UUID idUsuario);
    Optional<CarrinhoItem> findByCarrinhoAndProduto(Carrinho carrinho, Produto produto);

    void deleteByCarrinho(Carrinho carrinho);
}
