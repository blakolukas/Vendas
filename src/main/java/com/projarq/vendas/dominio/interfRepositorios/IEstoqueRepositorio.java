package com.projarq.vendas.dominio.interfRepositorios;

import java.util.List;

import com.projarq.vendas.dominio.entidades.ProdutoModel;

public interface IEstoqueRepositorio {
    List<ProdutoModel> todos();
    List<ProdutoModel> todosComEstoque();
    int quantidadeEmEstoque(long codigo);
    void baixaEstoque(long codProd, int qtdade);
}
