package com.projarq.vendas.dominio.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descricao;
    private double precoUnitario;

    // Construtor padrão exigido pelo JPA
    public ProdutoModel() {
    }

    // Novo construtor para criar ProdutoModel sem ID (para auto-geração)
    public ProdutoModel(String descricao, double precoUnitario) { // <--- NOVO CONSTRUTOR
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
    }

    public ProdutoModel(long id, String descricao, double precoUnitario) {
        this.id = id;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
    }

    public long getId() {
        return this.id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public double getPrecoUnitario() {
        return this.precoUnitario;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @Override
    public String toString() {
        return "{" +
                " codigo='" + getId() + "'" +
                ", descricao='" + getDescricao() + "'" +
                ", precoUnitario='" + getPrecoUnitario() + "'" +
                "}";
    }
}