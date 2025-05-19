package com.projarq.vendas.dominio.entidades;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PedidoModel {
    private long id;
    private String estado;
    private List<ItemPedidoModel> itens;

    public PedidoModel(long id, String estado) {
        this.id = id;
        this.estado = estado;
        this.itens = new LinkedList<>();
    }

    public long getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public List<ItemPedidoModel> getItens() {
        return new ArrayList<ItemPedidoModel>(itens);
    }

    public void addItem(ItemPedidoModel item){
        itens.add(item);
    }
}
