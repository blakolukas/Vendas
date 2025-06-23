package com.projarq.vendas.dominio.servicos;

import org.springframework.stereotype.Service;

import com.projarq.vendas.dominio.entidades.ItemPedidoModel;
import com.projarq.vendas.dominio.entidades.OrcamentoModel;
import com.projarq.vendas.dominio.entidades.PedidoModel;
import com.projarq.vendas.dominio.interfRepositorios.IEstoqueRepositorio;
import com.projarq.vendas.dominio.interfRepositorios.IOrcamentoRepositorio;

@Service
public class ServicoDeVendas {
    private final IOrcamentoRepositorio orcamentos;
    private final IEstoqueRepositorio estoque;
    private final ServicoDeEstoque servicoDeEstoque;

    //@Autowired
    public ServicoDeVendas(IOrcamentoRepositorio orcamentos, IEstoqueRepositorio estoque, ServicoDeEstoque servicoDeEstoque){
        this.orcamentos = orcamentos;
        this.estoque = estoque;
        this.servicoDeEstoque = servicoDeEstoque;
    }
     
    // Remover método produtosDisponiveis, pois não é mais utilizado e chama método inexistente
    // public List<ProdutoModel> produtosDisponiveis() {
    //     return estoque.todosComEstoque();
    // }

    public OrcamentoModel recuperaOrcamentoPorId(long id) {
        return this.orcamentos.recuperaPorId(id);
    }

    public OrcamentoModel criaOrcamento(PedidoModel pedido) {
        var novoOrcamento = new OrcamentoModel();
        novoOrcamento.addItensPedido(pedido);
        double custoItens = novoOrcamento.getItens().stream()
            .mapToDouble(it->it.getProduto().getPrecoUnitario()*it.getQuantidade())
            .sum();
        novoOrcamento.setCustoItens(custoItens); // Corrige: seta o custoItens no modelo
        
        // Aplica imposto por estado usando enum
        double imposto = 0.0;
        try {
            imposto = EstadoImposto.calcularImposto(pedido.getEstado(), custoItens, pedido);
        } catch (Exception e) {
            imposto = custoItens * 0.1;
        }
        novoOrcamento.setEstado(pedido.getEstado());
        novoOrcamento.setImposto(imposto);

        // Estratégias de desconto
        DescontoStrategy descontoPorItem = new DescontoPorItemStrategy();
        DescontoStrategy descontoPorTotalItens = new DescontoPorTotalItensStrategy();
        double desconto = descontoPorItem.calcularDesconto(novoOrcamento, pedido) + descontoPorTotalItens.calcularDesconto(novoOrcamento, pedido);

        novoOrcamento.setDesconto(desconto);
        novoOrcamento.setCustoConsumidor(custoItens + novoOrcamento.getImposto() - desconto);
        return this.orcamentos.cadastra(novoOrcamento);
    }
 
    public OrcamentoModel efetivaOrcamento(long id) {
        // Recupera o orçamento
        var orcamento = this.orcamentos.recuperaPorId(id);
        var ok = true;
        // Verifica se tem quantidade em estoque para todos os itens
        for (ItemPedidoModel itemPedido:orcamento.getItens()) {
            int qtdade = servicoDeEstoque.qtdadeEmEstoque(itemPedido.getProduto().getId());
            if (qtdade < itemPedido.getQuantidade()) {
                ok = false;
                break;
            }
        }
        // Verifica se o orçamento já tem menos de 21 dias
        if (orcamento.getDataCriacao().toLocalDate().isBefore(java.time.LocalDate.now().minusDays(21))) {
            ok = false;
            System.out.println("Orçamento com mais de 21 dias não pode ser efetivado.");
        }

        // Se tem quantidade para todos os itens, da baixa no estoque para todos
        if (ok) {
            for (ItemPedidoModel itemPedido:orcamento.getItens()) {
                int qtdade = servicoDeEstoque.qtdadeEmEstoque(itemPedido.getProduto().getId());
                if (qtdade >= itemPedido.getQuantidade()) {
                    servicoDeEstoque.baixaEstoque(itemPedido.getProduto().getId(), itemPedido.getQuantidade());
                }
            }
            // Marca o orcamento como efetivado
            orcamentos.marcaComoEfetivado(id);
        }
        // Retorna o orçamento marcado como efetivado ou não conforme disponibilidade do estoque
        return orcamentos.recuperaPorId(id);
    }
}
