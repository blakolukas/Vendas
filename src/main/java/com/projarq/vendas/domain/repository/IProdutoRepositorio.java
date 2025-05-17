package com.projarq.vendas.domain.repository;

import java.util.List;

import com.projarq.vendas.domain.model.Produto;

public interface IProdutoRepositorio {
    List<Produto> todos();
    Produto consultaPorId(long codigo);
}
