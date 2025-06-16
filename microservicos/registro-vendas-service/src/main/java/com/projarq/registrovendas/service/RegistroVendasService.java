package com.projarq.registrovendas.service;

import com.projarq.registrovendas.dto.RelatorioMensalResponse;
import com.projarq.registrovendas.dto.VendaEfetivadaMessage;
import com.projarq.registrovendas.entity.RegistroVenda;
import com.projarq.registrovendas.repository.RegistroVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroVendasService {

    @Autowired
    private RegistroVendaRepository repository;

    public void registrarVenda(VendaEfetivadaMessage message) {
        RegistroVenda registro = new RegistroVenda(
            message.getOrcamentoId(),
            message.getDataEfetivacao(),
            message.getValorCobrado(),
            message.getValorImpostos(),
            message.getEstado()
        );
        
        repository.save(registro);
        System.out.println("Venda registrada: Orçamento " + message.getOrcamentoId() + 
                          " - Valor: R$ " + message.getValorCobrado());
    }

    public RelatorioMensalResponse obterRelatorioMensal(int mes, int ano) {
        List<RegistroVenda> vendas = repository.findByAnoAndMes(ano, mes);
        Double totalVendido = repository.sumValorCobradoByAnoAndMes(ano, mes);
        Double totalImpostos = repository.sumValorImpostosByAnoAndMes(ano, mes);
        
        return new RelatorioMensalResponse(mes, ano, totalVendido, totalImpostos, vendas.size());
    }
}

