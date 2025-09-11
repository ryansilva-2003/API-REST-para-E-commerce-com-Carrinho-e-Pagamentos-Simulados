package com.ryanoliveira.ecommerce_api.repository;

import com.ryanoliveira.ecommerce_api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>  {
    boolean existsByNome(String Nome);
    Produto findByNome(String Nome);

}
