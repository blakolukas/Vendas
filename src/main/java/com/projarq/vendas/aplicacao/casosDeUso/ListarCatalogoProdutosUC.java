package com.projarq.vendas.aplicacao.casosDeUso;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projarq.vendas.aplicacao.dtos.ProdutoDTO;
import com.projarq.vendas.dominio.interfRepositorios.IProdutoRepositorio; // <--- Importação adicionada

@Component
public class ListarCatalogoProdutosUC {
    private IProdutoRepositorio produtoRepositorio; // <--- Novo atributo

    public ListarCatalogoProdutosUC(IProdutoRepositorio produtoRepositorio) { // <--- Injeção
        this.produtoRepositorio = produtoRepositorio;
    }

    public List<ProdutoDTO> run() {
        return produtoRepositorio.todos().stream() // Acessa todos os produtos do repositório
                .map(ProdutoDTO::fromModel)
                .toList();
    }
}