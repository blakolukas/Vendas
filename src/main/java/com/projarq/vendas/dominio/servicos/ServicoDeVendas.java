package com.projarq.vendas.dominio.servicos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projarq.vendas.dominio.entidades.ItemPedidoModel;
import com.projarq.vendas.dominio.entidades.OrcamentoModel;
import com.projarq.vendas.dominio.entidades.PedidoModel;
import com.projarq.vendas.dominio.entidades.ProdutoModel;
import com.projarq.vendas.dominio.interfRepositorios.IEstoqueRepositorio;
import com.projarq.vendas.dominio.interfRepositorios.IOrcamentoRepositorio;

@Service
public class ServicoDeVendas {
    private final IOrcamentoRepositorio orcamentos;
    private final IEstoqueRepositorio estoque;

    // @Autowired
    public ServicoDeVendas(IOrcamentoRepositorio orcamentos, IEstoqueRepositorio estoque) {
        this.orcamentos = orcamentos;
        this.estoque = estoque;
    }

    public List<ProdutoModel> produtosDisponiveis() {
        return estoque.todosComEstoque();
    }

    public OrcamentoModel recuperaOrcamentoPorId(long id) {
        return this.orcamentos.recuperaPorId(id);
    }

    public OrcamentoModel criaOrcamento(PedidoModel pedido) {
        var novoOrcamento = new OrcamentoModel();
        novoOrcamento.addItensPedido(pedido);
        double custoItens = novoOrcamento.getItens().stream()
                .mapToDouble(it -> it.getProduto().getPrecoUnitario() * it.getQuantidade())
                .sum();
        novoOrcamento.setCustoItens(custoItens);

        // Aplica imposto estadual/regional usando enum
        double impostoEstadual = 0.0;
        try {
            EstadoImposto estado = EstadoImposto.valueOf(pedido.getEstado());
            impostoEstadual = estado.calcularImposto(custoItens, pedido);
        } catch (IllegalArgumentException e) {
            // Se o estado não for suportado, uma regra padrão ou erro pode ser aplicada
            System.err.println("Estado não suportado para cálculo de imposto: " + pedido.getEstado());
            // Aqui você pode decidir se recusa o pedido ou aplica um imposto padrão
            impostoEstadual = 0.0; // Ou lance uma exceção para recusar o pedido
        }

        // Imposto federal
        double impostoFederal = custoItens * 0.15; // Alíquota única de 15%

        double impostoTotal = impostoEstadual + impostoFederal; // Impostos são calculados antes dos descontos

        novoOrcamento.setEstado(pedido.getEstado());
        novoOrcamento.setImposto(impostoTotal);

        // Estratégias de desconto
        DescontoStrategy descontoPorItem = new DescontoPorItemStrategy();
        DescontoStrategy descontoPorTotalItens = new DescontoPorTotalItensStrategy();
        double desconto = descontoPorItem.calcularDesconto(novoOrcamento, pedido)
                + descontoPorTotalItens.calcularDesconto(novoOrcamento, pedido);

        novoOrcamento.setDesconto(desconto);
        novoOrcamento.setCustoConsumidor(custoItens + novoOrcamento.getImposto() - desconto);
        return this.orcamentos.cadastra(novoOrcamento);
    }

    public OrcamentoModel efetivaOrcamento(long id) {
        // Recupera o orçamento
        var orcamento = this.orcamentos.recuperaPorId(id);
        
        // Verifica se o orçamento existe
        if (orcamento == null) {
            throw new IllegalArgumentException("Orçamento não encontrado.");
        }

        // Verifica se o orçamento já está efetivado
        if (orcamento.isEfetivado()) {
            System.out.println("Orçamento " + id + " já está efetivado.");
            return orcamento; // Retorna o orçamento já efetivado
        }

        // Verifica se o orçamento tem menos de 21 dias 
        if (orcamento.getDataCriacao().toLocalDate().isBefore(java.time.LocalDate.now().minusDays(21))) {
            System.out.println("Orçamento com mais de 21 dias não pode ser efetivado.");
            // Você pode lançar uma exceção ou retornar um DTO com status de erro aqui
            OrcamentoModel orcamentoExpirado = orcamento;
            orcamentoExpirado.efetiva(); // Marca como "não efetivado" ou um novo status para "expirado" se houver
            return orcamentoExpirado; // Retorna o orçamento como não efetivado
        }

        boolean viabilidadeVenda = true; // 
        // Verifica se tem quantidade em estoque para todos os itens
        for (ItemPedidoModel itemPedido:orcamento.getItens()) {
            int qtdade = estoque.quantidadeEmEstoque(itemPedido.getProduto().getId());
            if (qtdade == -1 || qtdade < itemPedido.getQuantidade()) { // Considera -1 para produto não encontrado ou sem estoque
                viabilidadeVenda = false;
                System.out.println("Estoque insuficiente para o produto: " + itemPedido.getProduto().getDescricao());
                break;
            }
        }

        // Se tem quantidade para todos os itens e é válido, dá baixa no estoque para todos e marca como efetivado
        if (viabilidadeVenda) { // 
            for (ItemPedidoModel itemPedido:orcamento.getItens()) {
                estoque.baixaEstoque(itemPedido.getProduto().getId(), itemPedido.getQuantidade());
            }
            // Marca o orcamento como efetivado 
            orcamentos.marcaComoEfetivado(id);
            System.out.println("Orçamento " + id + " efetivado com sucesso.");
        } else {
            System.out.println("Orçamento " + id + " não pode ser efetivado devido à falta de estoque.");
        }
        // Retorna o orçamento marcado como efetivado ou não conforme disponibilidade do estoque
        return orcamentos.recuperaPorId(id);
    }
}