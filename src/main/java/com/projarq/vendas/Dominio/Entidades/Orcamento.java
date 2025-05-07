package com.projarq.vendas.Dominio.Entidades;

import java.util.List;

public class Orcamento {
    private int id;
    private int idPedido;
    private double custoPedido;
    private double custoImposto;
    private double desconto;
    private double custoTotal;
    private boolean efetivado;
    private List<ItemPedido> itens;

    //TODO: Criar construtor com todos os atributos
}

