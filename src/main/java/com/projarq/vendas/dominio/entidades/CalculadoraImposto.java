package com.projarq.vendas.dominio.entidades;

import java.util.List;

public class CalculadoraImposto {

    public static double calcularImpostoEstadual(String estado, List<ItemPedidoModel> itens) {
        double total = itens.stream().mapToDouble(ItemPedidoModel::getSubtotal).sum();

        return switch (estado.toUpperCase()) {
            case "RS" -> total > 100.0 ? (total - 100.0) * 0.10 : 0.0;
            case "SP" -> total * 0.12;
            case "PE" -> itens.stream().mapToDouble(item -> {
                boolean essencial = item.getProduto().getDescricao().endsWith("*");
                double taxa = essencial ? 0.05 : 0.15;
                return item.getSubtotal() * taxa;
            }).sum();
            default -> throw new IllegalArgumentException("Estado não atendido: " + estado);
        };
    }

    public static double calcularImpostoFederal(List<ItemPedidoModel> itens) {
        double total = itens.stream().mapToDouble(ItemPedidoModel::getSubtotal).sum();
        return total * 0.15;
    }
}
