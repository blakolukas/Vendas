package com.projarq.vendas.dominio.servicos.impostos;

import com.projarq.vendas.dominio.entidades.OrcamentoModel;

public class ImpostoFederalFixoStrategy implements ImpostoStrategy {
    
    private static final double ALIQUOTA_PIS = 0.0165; // 1,65%
    private static final double ALIQUOTA_COFINS = 0.076; // 7,6%
    private static final double ALIQUOTA_TOTAL = ALIQUOTA_PIS + ALIQUOTA_COFINS; // 9,25%
    
    @Override
    public double calcularImposto(double valorBase, OrcamentoModel orcamento) {
        return valorBase * ALIQUOTA_TOTAL;
    }
    
    @Override
    public String getDescricao() {
        return "Impostos Federais (PIS/COFINS)";
    }
}

