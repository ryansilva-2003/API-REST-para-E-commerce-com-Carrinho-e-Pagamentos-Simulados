package com.ryanoliveira.ecommerce_api.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Pagamento")
public class Pagamento extends BaseEntity{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private UUID idPagamento;

    @ManyToOne
    @Column (name = "idUsuario", length = 36)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    private BigDecimal total;

    private String formaPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    private LocalDateTime dataPagamento;

    private Long idPedidoFinal;

    public Pagamento() {
        this.dataPagamento = LocalDateTime.now();
    }

    public Pagamento(UUID idPagamento) {
        this.idPagamento = idPagamento;
    }

    public UUID getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(UUID idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Long getIdPedidoFinal(){
        return idPedidoFinal;
    }

    public void setIdPedidoFinal(Long idPedidoFinal){
        this.idPedidoFinal = idPedidoFinal;
    }
}
