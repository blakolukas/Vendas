package com.projarq.vendas.aplicacao.dtos;

import java.util.LinkedList;
import java.util.List;

import com.projarq.vendas.dominio.entidades.ItemPedidoModel;
import com.projarq.vendas.dominio.entidades.OrcamentoModel;

public class OrcamentoDTO {
    private final long id;
    private final List<ItemPedidoDTO> itens;
    private final double custoItens;
    private final double imposto;
    private final double desconto;
    private final double custoConsumidor;
    private boolean efetivado;

    public OrcamentoDTO(OrcamentoModel orcamento) {
        this.id = orcamento.getId();
        this.custoItens = orcamento.getValorItens();
        this.imposto = orcamento.getImpostoEstadual() + orcamento.getImpostoFederal();
        this.desconto = orcamento.getDesconto();
        this.custoConsumidor = orcamento.getValorFinal();
        this.efetivado = orcamento.isEfetivado();
        this.itens = new LinkedList<>();
        for (ItemPedidoModel item : orcamento.getItens()) {
            itens.add(new ItemPedidoDTO(item.getProduto().getId(), item.getQuantidade()));
        }
    }

    public long getId() {
        return id;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public double getCustoItens() {
        return custoItens;
    }

    public double getImposto() {
        return imposto;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getCustoConsumidor() {
        return custoConsumidor;
    }

    public boolean isEfetivado() {
        return efetivado;
    }

    public void efetiva() {
        efetivado = true;
    }

    public static OrcamentoDTO fromModel(OrcamentoModel orcamento) {
        return new OrcamentoDTO(orcamento);
    }
}
