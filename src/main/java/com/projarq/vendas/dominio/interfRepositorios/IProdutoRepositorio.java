package com.projarq.vendas.dominio.interfRepositorios;

import java.util.List;

import com.projarq.vendas.dominio.entidades.ProdutoModel;

public interface IProdutoRepositorio {
    List<ProdutoModel> todos();
    ProdutoModel consultaPorId(long codigo);
}
