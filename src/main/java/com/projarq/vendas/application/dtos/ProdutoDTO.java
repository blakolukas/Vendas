package com.projarq.vendas.application.dtos;

import com.projarq.vendas.domain.model.Produto;

public class ProdutoDTO{
    private long id;
    private String descricao;
    private double precoUnitario;

    public ProdutoDTO(long id, String descricao, double precoUnitario) {
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

    public static ProdutoDTO fromModel(Produto produto){
        return new ProdutoDTO(produto.getId(), produto.getDescricao(), produto.getPrecoUnitario());
    }
}

