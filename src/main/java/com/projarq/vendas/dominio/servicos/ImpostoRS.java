package com.projarq.vendas.dominio.servicos;

import com.projarq.vendas.dominio.entidades.PedidoModel;

public class ImpostoRS implements ImpostoStrategy {
    @Override
    public double calcularImposto(double valorBase, PedidoModel pedido) {
        if(valorBase <=100){
            return 0;
        }
        return valorBase * 0.1;
    }
}
