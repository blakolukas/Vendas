package com.projarq.vendas.aplicacao.casosDeUso;

import java.util.List;

import com.projarq.vendas.aplicacao.dtos.ProdutoDTO;
import com.projarq.vendas.dominio.servicos.ServicoDeEstoque;

public class DispListaUC {
    private ServicoDeEstoque servicoEstoque;

    //@Autowired
    public DispListaUC(ServicoDeEstoque servicoEstoque){
        this.servicoEstoque = servicoEstoque;
    }

    public List<ProdutoDTO> run(List<ProdutoDTO> produtos){
        var idsRecebidos = produtos.stream().map(ProdutoDTO::getId).toList();
        return servicoEstoque.produtosDisponiveis().stream()
            .filter(p -> idsRecebidos.contains(p.getId()))
            .map(ProdutoDTO::fromModel)
            .toList();
    }
}
