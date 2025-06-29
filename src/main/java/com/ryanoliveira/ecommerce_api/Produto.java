package com.ryanoliveira.ecommerce_api;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table (name = "Produtos")
public class Produto {

    @Column (name = "idProduto")
    private String idProduto;

    @Column (name = "produtoNome", length = 90, nullable = false)
    @NotBlank (message = "Esse campo é obrigatório")
    private String produtoNome;

    @Column (name = "descricao", length = 200, nullable = false)
    @NotBlank (message = "Esse campo é obrigatório")
    private String descricao;

    @Column (name = "preco", nullable = false)
    @NotBlank (message = "Esse campo é obrigatório")
    private BigDecimal preco;

    @Column (name = "estoque", nullable = false)
    @NotBlank (message = "Esse campo é obrigatório")
    private Integer estoque;

    @Column (name = "categoria", nullable = false)
    @NotBlank (message = "Esse campo é obrigatório")
    private String categoria;

    public Produto(){
        this.idProduto = UUID.randomUUID().toString();
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public BigDecimal getPreco(){
        return preco;
    }

    public void setPreco(BigDecimal preco){
        this.preco = preco;
    }

    public Integer getEstoque(){
        return estoque;
    }

    public void setEstoque(Integer estoque){
        this.estoque = estoque;
    }


}
