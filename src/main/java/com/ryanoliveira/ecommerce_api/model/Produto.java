package com.ryanoliveira.ecommerce_api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table (name = "Produtos")
public class Produto extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "idProduto")
    private Long idProduto;

    @Column (name = "produtoNome", length = 90, nullable = false)
    @NotBlank (message = "Esse campo é obrigatório")
    private String nomeProduto;

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

    @NotBlank (message = "Esse campo é obrigatório")
    private String imagem;


    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
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

    public String getImagem(){
        return imagem;
    }

    public void setImagem(String imagem){
        this.imagem = imagem;
    }

    public String getCategoria(){
        return categoria;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

}
