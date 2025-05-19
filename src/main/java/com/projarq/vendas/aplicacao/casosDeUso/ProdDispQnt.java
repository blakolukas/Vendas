package com.projarq.vendas.aplicacao.casosDeUso;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projarq.vendas.aplicacao.dtos.ProdutoNomeQtdDTO;
import com.projarq.vendas.dominio.servicos.ServicoDeEstoque;

@Component
public class ProdDispQnt {
    private ServicoDeEstoque servicoEstoque;

    //@Autowired
    public ProdDispQnt(ServicoDeEstoque servicoEstoque){
        this.servicoEstoque = servicoEstoque;
    }

    public List<ProdutoNomeQtdDTO> run(){
        return servicoEstoque.produtosDisponiveis().stream()
            .map(p -> new ProdutoNomeQtdDTO(p.getDescricao(), servicoEstoque.qtdadeEmEstoque(p.getId())))
            .toList();
    }
    
}
