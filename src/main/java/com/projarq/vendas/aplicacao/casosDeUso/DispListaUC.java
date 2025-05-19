package com.projarq.vendas.aplicacao.casosDeUso;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projarq.vendas.aplicacao.dtos.ProdutoDTO;
import com.projarq.vendas.dominio.servicos.ServicoDeEstoque;

@Component
public class DispListaUC {
    private ServicoDeEstoque servicoEstoque;

    //@Autowired
    public DispListaUC(ServicoDeEstoque servicoEstoque){
        this.servicoEstoque = servicoEstoque;
    }

    public List<ProdutoDTO> run(List<Long> ids){
        return servicoEstoque.produtosDisponiveis().stream()
            .filter(p -> ids.contains(p.getId()))
            .map(ProdutoDTO::fromModel)
            .toList();
    }

    public List<String> run() {
        return servicoEstoque.produtosDisponiveis().stream()
            .map(ProdutoDTO::fromModel)
            .map(ProdutoDTO::getDescricao)
            .toList();
    }
}
