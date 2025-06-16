package com.projarq.vendas.dominio.servicos.impostos;

import com.projarq.vendas.dominio.entidades.OrcamentoModel;
import com.projarq.vendas.dominio.entidades.enums.Estado;

public class ImpostoEstadualStrategy implements ImpostoStrategy {
    
    @Override
    public double calcularImposto(double valorBase, OrcamentoModel orcamento) {
        try {
            Estado estado = Estado.fromSigla(orcamento.getEstado());
            
            // Diferentes alíquotas por estado
            switch (estado) {
                case SP:
                    return valorBase * 0.18; // 18% ICMS SP
                case RJ:
                    return valorBase * 0.20; // 20% ICMS RJ
                case RS:
                    return valorBase * 0.17; // 17% ICMS RS
                case MG:
                    return valorBase * 0.18; // 18% ICMS MG
                case PR:
                    return valorBase * 0.18; // 18% ICMS PR
                case SC:
                    return valorBase * 0.17; // 17% ICMS SC
                case BA:
                    return valorBase * 0.18; // 18% ICMS BA
                case GO:
                    return valorBase * 0.17; // 17% ICMS GO
                case DF:
                    return valorBase * 0.18; // 18% ICMS DF
                default:
                    return valorBase * 0.17; // 17% ICMS padrão para outros estados
            }
        } catch (IllegalArgumentException e) {
            // Estado não encontrado, aplica imposto padrão
            return valorBase * 0.10; // 10% imposto padrão
        }
    }
    
    @Override
    public String getDescricao() {
        return "Imposto Estadual (ICMS)";
    }
}

