package com.projarq.vendas.application.casosDeUso;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projarq.vendas.application.dtos.OrcamentoDTO;
import com.projarq.vendas.domain.model.Orcamento;
import com.projarq.vendas.domain.services.ServicoDeVendas;

@Component
public class EfetivaOrcamentoUC {
    private ServicoDeVendas servicoDeVendas;
    
    //@Autowired
    public EfetivaOrcamentoUC(ServicoDeVendas servicoDeVendas){
        this.servicoDeVendas = servicoDeVendas;
    }

    public OrcamentoDTO run(long idOrcamento){
        Orcamento orcamento =  servicoDeVendas.efetivaOrcamento(idOrcamento);
        return OrcamentoDTO.fromModel(orcamento);
    }
}