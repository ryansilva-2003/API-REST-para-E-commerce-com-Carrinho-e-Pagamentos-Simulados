package com.ryanoliveira.ecommerce_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column (name = "updated_at")
    private LocalDateTime updated_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdated(){
        this.updated_at = LocalDateTime.now();
    }

    public LocalDateTime getCreated_at(){
        return created_at;
    }

    public void setCreated_at(LocalDateTime Created_at){
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at(){
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}