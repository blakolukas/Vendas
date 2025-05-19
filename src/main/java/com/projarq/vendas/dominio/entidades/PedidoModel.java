package com.projarq.vendas.dominio.entidades;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PedidoModel {

    private final long id;
    private final String cliente;
    private final String estado;
    private final String pais;
    private final List<ItemPedidoModel> itens;

    public PedidoModel(long id, String cliente, String estado, String pais) {
        this.id = id;
        this.cliente = cliente;
        this.estado = estado;
        this.pais = pais;
        this.itens = new LinkedList<>();
    }

    public long getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }

    public List<ItemPedidoModel> getItens() {
        return new ArrayList<>(itens);
    }

    public void addItem(ItemPedidoModel item) {
        itens.add(item);
    }
}
