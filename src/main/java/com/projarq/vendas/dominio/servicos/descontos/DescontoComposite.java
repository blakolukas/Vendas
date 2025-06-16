package com.projarq.vendas.dominio.servicos.descontos;

import com.projarq.vendas.dominio.entidades.OrcamentoModel;
import java.util.List;
import java.util.ArrayList;

public class DescontoComposite implements DescontoStrategy {
    
    private List<DescontoStrategy> estrategias;
    
    public DescontoComposite() {
        this.estrategias = new ArrayList<>();
    }
    
    public void adicionarEstrategia(DescontoStrategy estrategia) {
        this.estrategias.add(estrategia);
    }
    
    public void removerEstrategia(DescontoStrategy estrategia) {
        this.estrategias.remove(estrategia);
    }
    
    @Override
    public double calcularDesconto(OrcamentoModel orcamento) {
        return estrategias.stream()
            .filter(estrategia -> estrategia.aplicavel(orcamento))
            .mapToDouble(estrategia -> estrategia.calcularDesconto(orcamento))
            .sum();
    }
    
    @Override
    public String getDescricao() {
        return "Desconto composto por múltiplas estratégias";
    }
    
    @Override
    public boolean aplicavel(OrcamentoModel orcamento) {
        return estrategias.stream()
            .anyMatch(estrategia -> estrategia.aplicavel(orcamento));
    }
    
    public List<DescontoStrategy> getEstrategiasAplicaveis(OrcamentoModel orcamento) {
        return estrategias.stream()
            .filter(estrategia -> estrategia.aplicavel(orcamento))
            .toList();
    }
}

