package com.projarq.vendas.aplicacao.casosDeUso;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projarq.vendas.aplicacao.dtos.ProdutoDTO;
import com.projarq.vendas.dominio.interfRepositorios.IProdutoRepositorio;

@Component
public class ListarCatalogoProdutosUC {
    private final IProdutoRepositorio produtoRepositorio; // <--- Adicionado 'final'

    public ListarCatalogoProdutosUC(IProdutoRepositorio produtoRepositorio) {
        this.produtoRepositorio = produtoRepositorio;
    }

    public List<ProdutoDTO> run() {
        return produtoRepositorio.todos().stream()
                .map(ProdutoDTO::fromModel)
                .toList();
    }
}