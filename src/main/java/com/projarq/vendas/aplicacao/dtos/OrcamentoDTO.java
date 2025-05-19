package com.projarq.vendas.aplicacao.dtos;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import com.projarq.vendas.dominio.entidades.ItemPedidoModel;
import com.projarq.vendas.dominio.entidades.OrcamentoModel;



public class OrcamentoDTO {
    private long id;
    private List<ItemPedidoDTO> itens;
    private double custoItens;
    private double imposto;
    private double desconto;
    private double custoConsumidor;
    private boolean efetivado;
    private String estado;
    private LocalDateTime dataCriacao;



    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public OrcamentoDTO(OrcamentoModel orcamento) {
        this.id = orcamento.getId();
        this.custoItens = orcamento.getCustoItens();
        this.imposto = orcamento.getImposto();
        this.desconto = orcamento.getDesconto();
        this.custoConsumidor = orcamento.getCustoConsumidor();
        this.estado = orcamento.getEstado();
        this.dataCriacao = orcamento.getDataCriacao();
        if (orcamento.isEfetivado()) this.efetivado = true;
        else this.efetivado = false;
        itens = new LinkedList<>();
        for(ItemPedidoModel item:orcamento.getItens()){
            itens.add(new ItemPedidoDTO(item.getProduto().getId(), item.getQuantidade()));
        }
    }

    public long getId() {
        return id;
    }

    public List<ItemPedidoDTO> getItens(){
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

    public void efetiva(){
        efetivado = true;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public static OrcamentoDTO fromModel(OrcamentoModel orcamento){
        return new OrcamentoDTO(orcamento);
    }
}
