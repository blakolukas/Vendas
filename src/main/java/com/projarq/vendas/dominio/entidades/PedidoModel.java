package com.projarq.vendas.dominio.entidades;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
    
    @Column(name = "estado")
    private String estado;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedidoModel> itens;

    public PedidoModel() {
        this.itens = new LinkedList<>();
    }

    public PedidoModel(long id, String estado) {
        this.id = id;
        this.estado = estado;
        this.itens = new LinkedList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ItemPedidoModel> getItens() {
        return new ArrayList<ItemPedidoModel>(itens);
    }

    public void addItem(ItemPedidoModel item){
        item.setPedido(this);
        itens.add(item);
    }
}
