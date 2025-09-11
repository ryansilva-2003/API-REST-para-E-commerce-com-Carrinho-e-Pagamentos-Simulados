package com.ryanoliveira.ecommerce_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ryanoliveira.ecommerce_api.model.Usuario;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    boolean existsByEmail(String email);
    Usuario findByEmail(String email);
    Usuario findByNome(String nome);
}
