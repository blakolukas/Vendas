package com.projarq.vendas.aplicacao.casosDeUso;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projarq.vendas.aplicacao.dtos.ItemPedidoDTO;
import com.projarq.vendas.aplicacao.dtos.OrcamentoDTO;
import com.projarq.vendas.dominio.entidades.ItemPedidoModel;
import com.projarq.vendas.dominio.entidades.OrcamentoModel;
import com.projarq.vendas.dominio.entidades.PedidoModel;
import com.projarq.vendas.dominio.entidades.ProdutoModel;
import com.projarq.vendas.dominio.servicos.ServicoDeEstoque;
import com.projarq.vendas.dominio.servicos.ServicoDeVendas;

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
        PedidoModel pedido = new PedidoModel(0, "Cliente Genérico", "RS", "BR");
        for(ItemPedidoDTO item:itens){
            ProdutoModel produto = servicoDeEstoque.produtoPorCodigo(item.getIdProduto());
            ItemPedidoModel itemPedido = new ItemPedidoModel(produto, item.getQtdade());
            pedido.addItem(itemPedido);
        }
        OrcamentoModel orcamento = servicoDeVendas.criaOrcamento(pedido);
        return OrcamentoDTO.fromModel(orcamento);
    }
}
