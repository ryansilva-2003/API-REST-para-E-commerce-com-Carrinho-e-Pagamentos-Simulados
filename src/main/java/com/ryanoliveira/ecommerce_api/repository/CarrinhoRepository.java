package com.ryanoliveira.ecommerce_api.repository;

import com.ryanoliveira.ecommerce_api.model.Carrinho;
import com.ryanoliveira.ecommerce_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    Optional<Carrinho> findByUsuario_idUsuario (UUID idUsuario);
    Optional<Carrinho> findByNome(Usuario usuario);

}
