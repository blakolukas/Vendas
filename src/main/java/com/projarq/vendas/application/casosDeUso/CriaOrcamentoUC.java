package com.projarq.vendas.application.casosDeUso;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projarq.vendas.application.dtos.ItemPedidoDTO;
import com.projarq.vendas.application.dtos.OrcamentoDTO;
import com.projarq.vendas.domain.model.ItemPedido;
import com.projarq.vendas.domain.model.Orcamento;
import com.projarq.vendas.domain.model.Pedido;
import com.projarq.vendas.domain.model.Produto;
import com.projarq.vendas.domain.services.ServicoDeEstoque;
import com.projarq.vendas.domain.services.ServicoDeVendas;

@Component
public class CriaOrcamentoUC {
    private final ServicoDeVendas servicoDeVendas;
    private final ServicoDeEstoque servicoDeEstoque;
    
    //@Autowired
    public CriaOrcamentoUC(ServicoDeVendas servicoDeVendas,ServicoDeEstoque servicoDeEstoque){
        this.servicoDeVendas = servicoDeVendas;
        this.servicoDeEstoque = servicoDeEstoque;
    }

    public OrcamentoDTO run(List<ItemPedidoDTO> itens){
        Pedido pedido = new Pedido(0);
        for(ItemPedidoDTO item:itens){
            Produto produto = servicoDeEstoque.produtoPorCodigo(item.getIdProduto());
            ItemPedido itemPedido = new ItemPedido(produto, item.getQtdade());
            pedido.addItem(itemPedido);
        }
        Orcamento orcamento = servicoDeVendas.criaOrcamento(pedido);
        return OrcamentoDTO.fromModel(orcamento);
    }
}
