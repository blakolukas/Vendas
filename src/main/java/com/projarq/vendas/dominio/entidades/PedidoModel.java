package com.projarq.vendas.dominio.entidades;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class PedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String estado;
    
    @OneToMany(mappedBy = "pedido")
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
