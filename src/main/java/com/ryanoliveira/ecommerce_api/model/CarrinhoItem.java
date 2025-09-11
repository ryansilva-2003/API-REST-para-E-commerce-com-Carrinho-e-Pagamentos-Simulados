package com.ryanoliveira.ecommerce_api.model;

import jakarta.persistence.*;


@Entity
@Table (name = "CarrinhoItem")
public class CarrinhoItem extends BaseEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "idCarrinhoItem")
    private Long idCarrinhoItem;

    @ManyToOne
    @JoinColumn (name = "idCarrinho", nullable = false)
    private Carrinho carrinho;

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

    public Carrinho getCarrinho(){
        return carrinho;
    }
    public void setCarrinho(Carrinho carrinho){
        this.carrinho = carrinho;
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
