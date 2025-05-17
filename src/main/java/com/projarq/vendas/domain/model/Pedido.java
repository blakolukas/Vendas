package com.projarq.vendas.domain.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Pedido {
    private long id;
    private List<ItemPedido> itens;

    public Pedido(long id) {
        this.id = id;
        this.itens = new LinkedList<>();
    }

    public long getId() {
        return id;
    }

    public List<ItemPedido> getItens() {
        return new ArrayList<>(itens);
    }

    public void addItem(ItemPedido item){
        itens.add(item);
    }
}
