package com.projarq.vendas.dominio.entidades;

import java.time.LocalDate;
import java.util.List;

public class OrcamentoModel {
    private long id;
    private final LocalDate data;
    private String cliente;
    private String estado;
    private String pais;
    private List<ItemPedidoModel> itens;
    private double valorItens;
    private double impostoEstadual;
    private double impostoFederal;
    private double desconto;
    private double valorFinal;
    private boolean efetivado;

    public OrcamentoModel() {
        this.data = LocalDate.now();
        this.efetivado = false;
    }

    public OrcamentoModel(long id, LocalDate data, String cliente, String estado, String pais,
            List<ItemPedidoModel> itens) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
        this.estado = estado;
        this.pais = pais;
        this.itens = itens;
        this.efetivado = false;
        this.calculaTotais();
    }

    public final void calculaTotais() {
        this.valorItens = itens.stream().mapToDouble(ItemPedidoModel::getSubtotal).sum();
        this.impostoEstadual = CalculadoraImposto.calcularImpostoEstadual(estado, itens);
        this.impostoFederal = CalculadoraImposto.calcularImpostoFederal(itens);
        this.desconto = calcularDesconto(itens);
        this.valorFinal = valorItens + impostoEstadual + impostoFederal - desconto;
    }

    private double calcularDesconto(List<ItemPedidoModel> itens) {
        double descontoPorItem = itens.stream()
                .filter(ItemPedidoModel::aplicaDescontoPorQuantidade)
                .mapToDouble(item -> item.getSubtotal() * 0.05)
                .sum();

        double totalItens = itens.stream().mapToDouble(ItemPedidoModel::getSubtotal).sum();
        double descontoPorQuantidadeTotal = itens.size() > 10 ? totalItens * 0.10 : 0;

        return descontoPorItem + descontoPorQuantidadeTotal;
    }

    public boolean estaValido() {
        return LocalDate.now().isBefore(data.plusDays(21));
    }

    public void marcarEfetivado() {
        this.efetivado = true;
    }

    public boolean isEfetivado() {
        return efetivado;
    }

    // Getters
    public long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
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
        return itens;
    }

    public double getValorItens() {
        return valorItens;
    }

    public double getImpostoEstadual() {
        return impostoEstadual;
    }

    public double getImpostoFederal() {
        return impostoFederal;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setItens(List<ItemPedidoModel> itens) {
        this.itens = itens;
    }

    public void setImpostoEstadual(double impostoEstadual) {
        this.impostoEstadual = impostoEstadual;
    }

    public void setImpostoFederal(double impostoFederal) {
        this.impostoFederal = impostoFederal;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }
}
