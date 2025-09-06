package com.ryanoliveira.ecommerce_api.repository;

import com.ryanoliveira.ecommerce_api.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {
}
