package com.projarq.registrovendas.listener;

import com.projarq.registrovendas.config.RabbitMQConfig;
import com.projarq.registrovendas.dto.VendaEfetivadaMessage;
import com.projarq.registrovendas.service.RegistroVendasService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendaEfetivadaListener {

    @Autowired
    private RegistroVendasService registroVendasService;

    @RabbitListener(queues = "#{rabbitMQConfig.getQueueName()}")
    public void receberVendaEfetivada(VendaEfetivadaMessage message) {
        System.out.println("Mensagem recebida: Orçamento " + message.getOrcamentoId() + " efetivado");
        registroVendasService.registrarVenda(message);
        System.out.println("Venda registrada no banco de dados local");
    }
}

