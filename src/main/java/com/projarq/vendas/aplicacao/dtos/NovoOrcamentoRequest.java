package com.projarq.vendas.aplicacao.dtos;

import java.util.List;

public class NovoOrcamentoRequest {
    private List<ItemPedidoDTO> itens;
    private String estado;

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
