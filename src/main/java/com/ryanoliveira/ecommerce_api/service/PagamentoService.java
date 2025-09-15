package com.ryanoliveira.ecommerce_api.service;

import com.ryanoliveira.ecommerce_api.model.Pagamento;
import com.ryanoliveira.ecommerce_api.model.StatusPagamento;
import com.ryanoliveira.ecommerce_api.model.StatusPedido;
import com.ryanoliveira.ecommerce_api.repository.PagamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PedidoFinalService pedidoFinalService;

    public PagamentoService(PagamentoRepository pagamentoRepository, PedidoFinalService pedidoFinalService) {
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoFinalService = pedidoFinalService;
    }

    @Transactional
    public Pagamento processarPagamento(Long idPedidoFinal, String formaPagamento, BigDecimal total) {
        Pagamento pagamento = new Pagamento();
        pagamento.setIdPagamento(UUID.randomUUID());
        pagamento.setIdPedidoFinal(idPedidoFinal);
        pagamento.setFormaPagamento(formaPagamento);
        pagamento.setTotal(total);
        pagamento.setStatusPagamento(StatusPagamento.PENDENTE);
        pagamento.setDataPagamento(LocalDateTime.now());

        pagamentoRepository.save(pagamento);

        pedidoFinalService.atualizarStatus(idPedidoFinal, StatusPedido.AGUARDANDO_PAGAMENTO);

        return pagamento;

    }

    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }

    public Optional<Pagamento> buscarPorId(UUID id) {
        return pagamentoRepository.findById(id);
    }

    @Transactional
    public Pagamento atualizarStatus(UUID idPagamento, StatusPagamento novoStatusPagamento) {
        Pagamento pagamento = pagamentoRepository.findById(idPagamento)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado com ID: " + idPagamento));
        pagamento.setStatusPagamento(novoStatusPagamento);
        pagamentoRepository.save(pagamento);

        StatusPedido statusPedidoCorrespondente = switch (novoStatusPagamento) {
            case PENDENTE -> StatusPedido.AGUARDANDO_PAGAMENTO;
            case APROVADO -> StatusPedido.PAGO;
            case RECUSADO -> StatusPedido.CANCELADO;
            case PROCESSANDO -> StatusPedido.EM_PREPARACAO;
            case CANCELADO -> StatusPedido.ESTORNO_PAGAMENTO;
        };
        pedidoFinalService.atualizarStatus(pagamento.getIdPedidoFinal(), statusPedidoCorrespondente);

        return pagamento;
    }

    @Transactional
    public void deletar(UUID idPagamento) {
        if (!pagamentoRepository.existsById(idPagamento)) {
            throw new RuntimeException("Pagamento não encontrado com ID: " + idPagamento);
        }
        pagamentoRepository.deleteById(idPagamento);
    }
}
