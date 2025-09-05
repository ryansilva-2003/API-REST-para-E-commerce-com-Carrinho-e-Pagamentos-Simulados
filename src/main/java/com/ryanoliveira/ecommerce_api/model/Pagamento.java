package com.ryanoliveira.ecommerce_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Pagamento")
public class Pagamento extends BaseEntity{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
}
