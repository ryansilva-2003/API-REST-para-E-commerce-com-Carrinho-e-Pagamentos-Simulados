package com.ryanoliveira.ecommerce_api.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name = "PedidoItem")
public class PedidoItem extends BaseEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "idPedidoItem")
    private Long idPedidoItem;

    @ManyToOne
    @JoinColumn (name = "idProduto", nullable = false)
    private Produto produto;

    @Column (name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column (name = "precoUnitario", nullable = false)
    private BigDecimal precoUnitario;

    @ManyToOne
    @JoinColumn (name = "idPedidoFinal", nullable = false)
    private PedidoFinal pedidoFinal;

    public Long getIdPedidoItem(){
        return idPedidoItem;
    }

    public void setIdPedidoItem(Long idPedidoItem){
        this.idPedidoItem = idPedidoItem;
    }

    public Produto getProduto(){
        return produto;
    }

    public void setProduto(Produto produto){
        this.produto = produto;
    }

    public Integer getQuantidade(){
        return quantidade;
    }

    public void setQuantidade(Integer quantidade){
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario(){
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario){
        this.precoUnitario = precoUnitario;
    }

    public PedidoFinal getPedidoFinal(){
        return pedidoFinal;
    }

    public void setPedidoFinal(PedidoFinal pedidoFinal){
        this.pedidoFinal = pedidoFinal;
    }

}
