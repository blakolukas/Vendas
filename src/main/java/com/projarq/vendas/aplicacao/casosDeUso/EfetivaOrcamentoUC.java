package com.projarq.vendas.aplicacao.casosDeUso;

//import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.projarq.vendas.aplicacao.dtos.OrcamentoDTO;
import com.projarq.vendas.config.RabbitMQConfig;
import com.projarq.vendas.dominio.entidades.OrcamentoModel;
import com.projarq.vendas.dominio.servicos.ServicoDeVendas;

@Component
public class EfetivaOrcamentoUC {
    private ServicoDeVendas servicoDeVendas;
    private final RabbitTemplate rabbitTemplate;
    
    //@Autowired
    public EfetivaOrcamentoUC(ServicoDeVendas servicoDeVendas, RabbitTemplate rabbitTemplate){
        this.servicoDeVendas = servicoDeVendas;
        this.rabbitTemplate = rabbitTemplate;
    }

    public OrcamentoDTO run(long idOrcamento){
        OrcamentoModel orcamento =  servicoDeVendas.efetivaOrcamento(idOrcamento);
        OrcamentoDTO dto = OrcamentoDTO.fromModel(orcamento);
        if (dto.isEfetivado()) {
            OrcamentoEfetivadoMessage msg = new OrcamentoEfetivadoMessage();
            msg.custoItens = dto.getCustoItens();
            msg.imposto = dto.getImposto();
            msg.desconto = dto.getDesconto();
            msg.custoConsumidor = dto.getCustoConsumidor();
            msg.estado = dto.getEstado();
            msg.dataCriacao = dto.getDataCriacao().toString();
            msg.itens = dto.getItens().stream().map(Object::toString).collect(Collectors.toList());
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, msg);
        }
        return dto;
    }

    public static class OrcamentoEfetivadoMessage {
        public double custoItens;
        public double imposto;
        public double desconto;
        public double custoConsumidor;
        public String estado;
        public String dataCriacao;
        public java.util.List<String> itens;
    }
}