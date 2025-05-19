package com.projarq.vendas.dominio.entidades;

public class ItemPedidoModel {
    private final ProdutoModel produto;
    private final int quantidade;

    public ItemPedidoModel(ProdutoModel produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubtotal() {
        return produto.getPrecoUnitario() * quantidade;
    }

    public boolean aplicaDescontoPorQuantidade() {
        return quantidade > 3;
    }
}
