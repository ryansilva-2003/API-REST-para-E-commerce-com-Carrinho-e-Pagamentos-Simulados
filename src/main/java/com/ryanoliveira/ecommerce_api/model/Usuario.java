package com.ryanoliveira.ecommerce_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table (name = "Usuario")
public class Usuario extends BaseEntity {

    @Id
    @Column (name = "idUsuario", length = 36)
    private UUID idUsuario;

    @Column (name = "nome", length = 90, nullable = false)
    @NotBlank (message = "Esse campo é obrigatório")
    private String nome;

    @Column (name = "email", length = 45, nullable = false, unique = true)
    @Email (message = "Esse email deve ser válido")
    @NotBlank (message = "Esse campo é obrigatório")
    private String email;

    @Column (name = "senha", length = 255, nullable = false)
    @NotBlank (message = "Esse campo é obrigatório")
    private String senha;

    @Column (name = "data_nasc", nullable = false)
    @NotBlank (message = "Esse campo é obrigatório")
    private LocalDate dataNasc;

    @Column (name = "celular", length = 20, nullable = false)
    @NotBlank (message = "Esse campo é obrigatório")
    private String celular;

    public Usuario(){
        this.idUsuario = UUID.randomUUID().toUUID();
    }

    public UUID getNome() {
        return nome;
    }

    public void setNome(UUID nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getCelular(){
        return celular;
    }

    public void setCelular(String Celular){
        this.celular = celular;
    }
}
