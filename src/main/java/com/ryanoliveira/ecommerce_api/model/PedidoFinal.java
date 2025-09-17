package com.ryanoliveira.ecommerce_api.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table (name = "PedidoFinal")
public class PedidoFinal {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "idPedidoFinal")
    private Long idPedidoFinal;

    @ManyToOne
    @JoinColumn (name = "idUsuario", nullable = false)
    private Produto produto;

    @Column (name = "dataCriacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column (name = "status", nullable = false)
    private StatusPedido statusPedido;

    @Column (name = "total", nullable = false)
    private BigDecimal total;

    public Long getIdPedidoFinal(){
        return idPedidoFinal;
    }

    public void setIdPedidoFinal(Long idPedidoFinal){
        this.idPedidoFinal = idPedidoFinal;
    }

    public Produto getProduto(){
        return produto;
    }

    public void setProduto(Produto produto){
        this.produto = produto;
    }

    public LocalDateTime getDataCriacao(){
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao){
        this.dataCriacao = dataCriacao;
    }

    public StatusPedido getStatusPedido(){
        return statusPedido;
    }

    public void setStatusPedido (StatusPedido statusPedido){
        this.statusPedido = statusPedido;
    }

    public BigDecimal getTotal(){
        return total;
    }

    public void setTotal(BigDecimal total){
        this.total = total;
    }


}
