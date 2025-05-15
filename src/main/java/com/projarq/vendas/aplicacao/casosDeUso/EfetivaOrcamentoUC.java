package com.projarq.vendas.aplicacao.casosDeUso;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projarq.vendas.aplicacao.dtos.OrcamentoDTO;
import com.projarq.vendas.dominio.entidades.OrcamentoModel;
import com.projarq.vendas.dominio.servicos.ServicoDeVendas;

@Component
public class EfetivaOrcamentoUC {
    private ServicoDeVendas servicoDeVendas;
    
    //@Autowired
    public EfetivaOrcamentoUC(ServicoDeVendas servicoDeVendas){
        this.servicoDeVendas = servicoDeVendas;
    }

    public OrcamentoDTO run(long idOrcamento){
        OrcamentoModel orcamento =  servicoDeVendas.efetivaOrcamento(idOrcamento);
        return OrcamentoDTO.fromModel(orcamento);
    }
}