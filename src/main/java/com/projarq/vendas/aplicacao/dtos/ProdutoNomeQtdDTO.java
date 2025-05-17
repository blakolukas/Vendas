package com.projarq.vendas.aplicacao.dtos;

public class ProdutoNomeQtdDTO {
    private String descricao;
    private int quantidade;

    public ProdutoNomeQtdDTO(String descricao, int quantidade) {
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
