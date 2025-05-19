package com.projarq.vendas.aplicacao.dtos;

public class ProdutoNomeQtdDTO {
    private long id;
    private String descricao;
    private int quantidade;

    public ProdutoNomeQtdDTO(long id, String descricao, int quantidade) {
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
