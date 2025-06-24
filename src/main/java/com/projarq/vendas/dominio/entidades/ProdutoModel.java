package com.projarq.vendas.dominio.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class ProdutoModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "preco_unitario")
    private double precoUnitario;

    public ProdutoModel() {
    }

    public ProdutoModel(String descricao, double precoUnitario) {
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoUnitario() {
        return this.precoUnitario;
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