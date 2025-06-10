package com.projarq.vendas.aplicacao.casosDeUso;

import org.springframework.stereotype.Component;

import com.projarq.vendas.dominio.servicos.ServicoDeEstoque;

@Component
public class EntradaEstoqueUC {
    private ServicoDeEstoque servicoEstoque;

    public EntradaEstoqueUC(ServicoDeEstoque servicoEstoque) {
        this.servicoEstoque = servicoEstoque;
    }

    public void run(long idProduto, int quantidade) {
        servicoEstoque.entradaEstoque(idProduto, quantidade);
    }
}