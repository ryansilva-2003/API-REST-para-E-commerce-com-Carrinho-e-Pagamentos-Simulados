package com.ryanoliveira.ecommerce_api.model;

import jakarta.persistence.*;
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

    private Double valor;

    private String formaPagamento;

    @Enumerated(EnumType.STRING)
    private String statusPagamento;

    private LocalDateTime dataPagamento;

    public Pagamento() {
        this.dataPagamento = LocalDateTime.now();
        this.statusPagamento = "PENDENTE";
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
