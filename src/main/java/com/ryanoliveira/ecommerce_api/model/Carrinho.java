package com.ryanoliveira.ecommerce_api.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Carrinho")
public class Carrinho extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCarrinho")
    private Long idCarrinho;

    @OneToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarrinhoItem> itens = new ArrayList<>();

    public Long getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(Long idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<CarrinhoItem> getItens() {
        return itens;
    }

    public void setItens(List<CarrinhoItem> itens) {
        this.itens = itens;
    }
}
