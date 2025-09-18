package com.ryanoliveira.ecommerce_api.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "PedidoFinal")
public class PedidoFinal {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "idPedidoFinal")
    private Long idPedidoFinal;

    @ManyToOne
    @JoinColumn (name = "idProduto", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedidoFinal", cascade = CascadeType.ALL)
    private List<PedidoItem> itens;

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

    public Usuario getUsuario(){
        return usuario;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public LocalDateTime getDataCriacao(){
        return dataCriacao;
    }

    public List<PedidoItem> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItem> itens) {
        this.itens = itens;
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
