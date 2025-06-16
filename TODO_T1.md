# TODO - Implementação T1

## Pessoa A - Domínio & Regras de Negócio

### Fase 1 (isolada)
- [x] Criar estratégias de impostos (ImpostoEstadualStrategy, ImpostoFederalFixoStrategy)
- [x] Implementar Regras de Desconto (>3 itens e >10 itens) usando Strategy/Composite
- [x] Adicionar dataCriacao + método isValido() (21 dias) em OrcamentoModel
- [x] Criar enum Pais/Estado + verificação de cobertura geográfica
- [x] Refatorar ServicoDeVendas para usar novas regras e lançar exceções de domínio

### Fase 2 (integração)
- [ ] Conectar ServicoDeVendas ao OrcamentoRepJPA pronto (Pessoa B)
- [ ] Ajustar testes que cobrem fluxo cria → efetiva

### Extras
- [ ] Cobertura de testes de unidade das estratégias

## Pessoa B - Persistência (JPA) & Seed

### Fase 1 (isolada)
- [ ] Criar entidades Orcamento e ItemOrcamento (JPA)
- [ ] Criar interface OrcamentoJPA_ItfRep (CrudRepository)
- [ ] Implementar OrcamentoRepJPA por completo (mapeamentos Model ↔ Entity)
- [ ] Ajustar application.properties (DDL auto) e data.sql (opcional)

### Fase 2 (integração)
- [ ] Validar conversão dos novos campos criados pela Pessoa A
- [ ] Atender chamadas feitas pelos novos Use Cases da Pessoa C

### Extras
- [ ] Criar script de liquibase ou flyway (opcional)

## Pessoa C - Use Cases, Controllers & Testes de Integração

### Fase 1 (isolada)
- [ ] Definir DTOs (ProdutoCatalogoDTO, EstoqueDTO, EntradaEstoqueDTO, etc.)
- [ ] Implementar novos Use Cases: ListaCatálogoUC, EntradaEstoqueUC, EstoqueTotalUC, EstoquePorListaUC, OrcamentosEfetivadosPeriodoUC
- [ ] Expor REST endpoints no Controller

### Fase 2 (integração)
- [ ] Conectar Use Cases aos serviços/refatorações da Pessoa A e repositórios da Pessoa B
- [ ] Criar SpringBootTest cobrindo criação → efetivação e entrada/consulta de estoque

### Extras
- [ ] Atualizar coleção Postman e ajustar CORS/global exception handler

## Checklist de Integração Final
- [ ] Build passa: ./mvnw clean verify
- [ ] Testes verdes (unit + integration)
- [ ] Sonar/Qodana sem code smells críticos
- [ ] Docker Compose opcional com banco H2/Postgres
- [ ] README.md com instruções e coleção Postman atualizados

