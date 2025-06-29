package com.ryanoliveira.ecommerce_api;

import jakarta.persistence.*;


@Entity
@Table (name = "CarrinhoItem")
public class CarrinhoItem {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "idCarrinhoItem")
    private Long idCarrinhoItem;

    @ManyToOne
    @JoinColumn (name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn (name = "idProduto", nullable = false)
    private Produto produto;

    @Column (name = "quantidade", nullable = false)
    private Integer quantidade;

    public Long getIdCarrinhoItem(){
        return idCarrinhoItem;
    }

    public void setIdCarrinhoItem(Long idCarrinhoItem) {
        this.idCarrinhoItem = idCarrinhoItem;
    }

    public Usuario getUsuario(){
        return usuario;
    }
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
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

}
