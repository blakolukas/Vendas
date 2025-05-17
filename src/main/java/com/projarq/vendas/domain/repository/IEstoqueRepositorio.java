package com.projarq.vendas.domain.repository;

import java.util.List;

import com.projarq.vendas.domain.model.Produto;

public interface IEstoqueRepositorio {
    List<Produto> todos();
    List<Produto> todosComEstoque();
    int quantidadeEmEstoque(long codigo);
    int baixaEstoque(long codProd, int qtdade);
}
