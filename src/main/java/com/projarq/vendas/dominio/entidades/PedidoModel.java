package com.projarq.vendas.dominio.entidades;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PedidoModel {
    private final long id; // <--- Adicionado 'final'
    private final String estado; // <--- Adicionado 'final'
    private final List<ItemPedidoModel> itens; // <--- Adicionado 'final'

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
        return new ArrayList<>(itens); // <--- Usado o operador diamante '<>'
    }

    public void addItem(ItemPedidoModel item) {
        itens.add(item);
    }
}